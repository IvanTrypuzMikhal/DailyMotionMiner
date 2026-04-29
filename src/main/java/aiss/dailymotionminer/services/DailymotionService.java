package aiss.dailymotionminer.services;

import aiss.dailymotionminer.etl.Transformer;
import aiss.dailymotionminer.models.dailymotionminerobjects.*;
import aiss.dailymotionminer.models.videoMinerObjects.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Service
public class DailymotionService {

    private static final String DAILYMOTION_BASE_URL = "https://api.dailymotion.com";

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${videominer.base-url}")
    private String videoMinerBaseUrl;

    @Value("${videominer.channels-path}")
    private String videoMinerChannelsPath;

    public VMChannel getChannel(String id, Integer maxVideos, Integer maxPages) {
        maxVideos = maxVideos == null ? 10 : maxVideos;
        maxPages = maxPages == null ? 2 : maxPages;

        DMChannel dmChannel = getDMChannel(id);
        DMAccount dmOwner = getDMOwner(id);

        List<DMVideo> dmVideos = getDMVideos(id, maxVideos, maxPages);
        List<VMVideo> vmVideos = new ArrayList<>();

        for (DMVideo dmVideo : dmVideos) {
            List<VMCaption> captions = getVMCaptions(dmVideo.getId());

            VMVideo vmVideo = Transformer.videoTransformer(
                    dmVideo,
                    dmOwner,
                    captions
            );

            vmVideos.add(vmVideo);
        }

        VMChannel vmChannel = Transformer.channelTransformer(dmChannel);
        vmChannel.setVideos(vmVideos);

        return vmChannel;
    }

    public VMChannel sendChannelToVideoMiner(String id, Integer maxVideos, Integer maxPages) {
        VMChannel vmChannel = getChannel(id, maxVideos, maxPages);

        String url = videoMinerBaseUrl + videoMinerChannelsPath;

        try {
            ResponseEntity<VMChannel> response = restTemplate.postForEntity(
                    url,
                    vmChannel,
                    VMChannel.class
            );

            return response.getBody();

        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "VideoMiner endpoint not found: " + url
            );

        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(
                    e.getStatusCode(),
                    "VideoMiner rejected the channel: " + e.getResponseBodyAsString()
            );
        }
    }

    private DMChannel getDMChannel(String id) {
        String url = DAILYMOTION_BASE_URL + "/user/" + id
                + "?fields=id,username,description,created_time";

        try {
            return restTemplate.getForObject(url, DMChannel.class);

        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Dailymotion channel not found: " + id
            );

        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(
                    e.getStatusCode(),
                    "Dailymotion error while loading channel: " + e.getResponseBodyAsString()
            );
        }
    }

    private DMAccount getDMOwner(String id) {
        String url = DAILYMOTION_BASE_URL + "/user/" + id
                + "?fields=id,username,url,avatar_120_url";

        try {
            return restTemplate.getForObject(url, DMAccount.class);

        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Dailymotion owner not found: " + id
            );

        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(
                    e.getStatusCode(),
                    "Dailymotion error while loading owner: " + e.getResponseBodyAsString()
            );
        }
    }

    private List<DMVideo> getDMVideos(String channelId, Integer maxVideos, Integer maxPages) {
        List<DMVideo> allVideos = new ArrayList<>();

        for (int page = 1; page <= maxPages && allVideos.size() < maxVideos; page++) {
            String url = DAILYMOTION_BASE_URL + "/user/" + channelId + "/videos"
                    + "?fields=id,title,channel,description,created_time,tags"
                    + "&limit=" + maxVideos
                    + "&page=" + page;

            DMVideoSearch response = restTemplate.getForObject(url, DMVideoSearch.class);

            if (response == null || response.getList() == null) {
                break;
            }

            for (DMVideo video : response.getList()) {
                if (allVideos.size() < maxVideos) {
                    allVideos.add(video);
                }
            }

            if (response.getHasMore() == null || !response.getHasMore()) {
                break;
            }
        }

        return allVideos;
    }

    private List<VMCaption> getVMCaptions(String videoId) {
        List<VMCaption> captions = new ArrayList<>();

        String url = DAILYMOTION_BASE_URL + "/video/" + videoId + "/subtitles"
                + "?fields=id,url,language";

        try {
            DMSubtitleSearch response = restTemplate.getForObject(url, DMSubtitleSearch.class);

            if (response == null || response.getList() == null) {
                return captions;
            }

            for (DMSubtitle subtitle : response.getList()) {
                captions.add(Transformer.captionTransformer(subtitle));
            }

        } catch (Exception e) {
            return captions;
        }

        return captions;
    }
}