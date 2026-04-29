package aiss.dailymotionminer.etl;

import aiss.dailymotionminer.models.dailymotionminerobjects.*;
import aiss.dailymotionminer.models.videoMinerObjects.*;

import java.util.ArrayList;
import java.util.List;

public class Transformer {

    public static VMCaption captionTransformer(DMSubtitle dmSubtitle) {
        return new VMCaption(
                dmSubtitle.getId(),
                dmSubtitle.getUrl(),
                dmSubtitle.getLanguage()
        );
    }

    public static VMChannel channelTransformer(DMChannel dmChannel) {
        return new VMChannel(
                dmChannel.getId(),
                dmChannel.getUsername(),
                dmChannel.getDescription(),
                dmChannel.getCreated_time(),
                new ArrayList<>()
        );
    }

    public static VMUser accountTransformer(DMAccount dmUser) {
        if (dmUser == null) {
            return null;
        }

        return new VMUser(
                dmUser.getId(),
                dmUser.getUsername(),
                dmUser.getUrl(),
                dmUser.getAvatar_url()
        );
    }

    public static List<VMComment> commentsFromTags(DMVideo dmVideo) {
        List<VMComment> comments = new ArrayList<>();

        if (dmVideo.getTags() == null || dmVideo.getTags().isEmpty()) {
            return comments;
        }

        for (int i = 0; i < dmVideo.getTags().size(); i++) {
            String tag = dmVideo.getTags().get(i);

            comments.add(new VMComment(
                    dmVideo.getId() + "-tag-" + i,
                    tag,
                    dmVideo.getCreatedTime()
            ));
        }

        return comments;
    }

    public static VMVideo videoTransformer(DMVideo dmVideo, DMAccount dmOwner, List<VMCaption> captions) {
        if (captions == null) {
            captions = new ArrayList<>();
        }

        return new VMVideo(
                dmVideo.getId(),
                dmVideo.getTitle(),
                dmVideo.getDescription(),
                dmVideo.getCreatedTime(),
                accountTransformer(dmOwner),
                commentsFromTags(dmVideo),
                captions
        );
    }
}