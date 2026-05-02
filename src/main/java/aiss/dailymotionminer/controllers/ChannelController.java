package aiss.dailymotionminer.controllers;

import aiss.dailymotionminer.exception.ChannelNotFoundException;
import aiss.dailymotionminer.models.videoMinerObjects.VMChannel;
import aiss.dailymotionminer.services.DailymotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dailymotion")
public class ChannelController {

    @Autowired
    private DailymotionService dailymotionService;

    @GetMapping("/{id}")
    public VMChannel getChannel(
            @PathVariable String id,
            @RequestParam(required = false) Integer maxVideos,
            @RequestParam(required = false) Integer maxPages
    ) throws ChannelNotFoundException {
        try {
            return dailymotionService.getChannel(id, maxVideos, maxPages);
        } catch (Exception e) {
            throw new ChannelNotFoundException();
        }

    }


    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public VMChannel sendChannelToVideoMiner(
            @PathVariable String id,
            @RequestParam(required = false) Integer maxVideos,
            @RequestParam(required = false) Integer maxPages
    ) throws ChannelNotFoundException{
        try {
            return dailymotionService.sendChannelToVideoMiner(id, maxVideos, maxPages);
        } catch (Exception e) {
            throw new ChannelNotFoundException();
        }
    }
}