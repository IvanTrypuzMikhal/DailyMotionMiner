package aiss.dailymotionminer.controllers;

import aiss.dailymotionminer.exception.ChannelNotFoundException;
import aiss.dailymotionminer.models.videoMinerObjects.VMChannel;
import aiss.dailymotionminer.services.DailymotionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Dailymotion Channels",
        description = "Operations to retrieve Dailymotion channels and transform them to the VideoMiner format"
)


@RestController
@RequestMapping("/dailymotion")
public class ChannelController {

    @Autowired
    private DailymotionService dailymotionService;

    @Operation(
            summary = "Get a Dailymotion channel",
            description = "Gets a Dailymotion channel by id (e.g., elmundo), optionally including a limited number of videos and tags per video. By default, the limits are 10 videos and 10 tags.",
            tags = { "Dailymotion Channels" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Channel retrieved successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = VMChannel.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Channel not found in Dailymotion",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    public VMChannel getChannel(
            @Parameter(
                    description = "Name of the Dailymotion channel to retrieve",
                    example = "elmundo"
            )
            @PathVariable String id,

            @Parameter(
                    description = "Maximum number of videos to include in the channel",
                    example = "10"
            )
            @RequestParam(defaultValue = "10") Integer maxVideos,

            @Parameter(
                    description = "Maximum number of tags to include per video",
                    example = "10"
            )
            @RequestParam(defaultValue = "10") Integer maxPages
    ) throws ChannelNotFoundException {
        try {
            return dailymotionService.getChannel(id, maxVideos, maxPages);
        } catch (Exception e) {
            throw new ChannelNotFoundException();
        }

    }

    @Operation(
            summary = "Get and send a channel to VideoMiner",
            description = "Gets a Dailymotion channel, transforms it to the VideoMiner format, and sends it via a POST request to the VideoMiner service.",
            tags = { "Dailymotion Channels" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Channel retrieved and sent to VideoMiner successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = VMChannel.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Channel not found in Dailymotion",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal error while retrieving or sending the channel",
                    content = @Content
            )
    })
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public VMChannel sendChannelToVideoMiner(
            @Parameter(
                    description = "Name of the Dailymotion channel to retrieve and send to VideoMiner",
                    example = "elmundo"
            )
            @PathVariable String id,

            @Parameter(
                    description = "Maximum number of videos to include in the channel",
                    example = "10"
            )
            @RequestParam(defaultValue = "10") Integer maxVideos,

            @Parameter(
                    description = "Maximum number of tags to include per video",
                    example = "10"
            )
            @RequestParam(defaultValue = "10") Integer maxPages
    ) throws ChannelNotFoundException{
        try {
            return dailymotionService.sendChannelToVideoMiner(id, maxVideos, maxPages);
        } catch (Exception e) {
            throw new ChannelNotFoundException();
        }
    }
}