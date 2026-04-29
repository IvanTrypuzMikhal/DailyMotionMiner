package aiss.dailymotionminer.controllers;

import aiss.dailymotionminer.models.videoMinerObjects.VMChannel;
import aiss.dailymotionminer.services.DailymotionService;
import org.springframework.beans.factory.annotation.Autowired;
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
    ) {
        return dailymotionService.getChannel(id, maxVideos, maxPages);
    }

    @PostMapping("/{id}")
    public VMChannel sendChannelToVideoMiner(
            @PathVariable String id,
            @RequestParam(required = false) Integer maxVideos,
            @RequestParam(required = false) Integer maxPages
    ) {
        return dailymotionService.sendChannelToVideoMiner(id, maxVideos, maxPages);
    }
}