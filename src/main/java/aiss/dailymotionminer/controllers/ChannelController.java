/**
 * Autor: Noah Karst
 * Matrikelnummer: 21683
 * Umgebung: IntelliJ, Win11
 * Datum: 14.12.2025
 */

package aiss.dailymotionminer.controllers;

import aiss.dailymotionminer.models.videoMinerObjects.VMChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/peertube/channel")
public class ChannelController {


    @Autowired
    public ChannelService channelService;

    @GetMapping("/{channelName}")
    public VMChannel getChannel(@PathVariable String channelName){
        return channelService.buildChannel(channelName);
    }

}
