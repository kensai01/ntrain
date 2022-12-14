package com.nitetrain.web.rest;

import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.Video;
import com.nitetrain.domain.youtube.PlaylistVideos;
import com.nitetrain.service.impl.YouTubeApiServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link com.nitetrain.domain.Workout}.
 */
@RestController
@RequestMapping("/api")
public class YouTubeResource {

    private final Logger log = LoggerFactory.getLogger(YouTubeResource.class);
    private YouTubeApiServiceImpl youTubeApiService;

    @Autowired
    public YouTubeResource(YouTubeApiServiceImpl youTubeApiService) {
        this.youTubeApiService = youTubeApiService;
    }

    /**
     * {@code GET  /playlist} : get all the playlists with video ids.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of playlist videos in body.
     */
    @GetMapping("/pages/videos/{channelId}")
    public ResponseEntity<List<PlaylistVideos>> getAllVideos(@PathVariable String channelId) {
        log.info("REST request to get all playlists and corresponding video ids, channelId=" + channelId);
        List<PlaylistVideos> result = new ArrayList<>();
        try {
            List<Playlist> playlists = youTubeApiService.getPlaylistsForChannelId(channelId);
            playlists.forEach(playlist -> {
                PlaylistVideos playlistVideos = new PlaylistVideos();
                playlistVideos.setPlaylistTitle(playlist.getSnippet().getTitle());
                playlistVideos.setDescription(playlist.getSnippet().getDescription());
                try {
                    List<PlaylistItem> playlistItems = youTubeApiService.getPlaylistItems(playlist.getId());
                    List<Video> videoListToAdd = new ArrayList<>();
                    playlistItems.forEach(playlistItem -> {
                        try {
                            List<Video> videoList = youTubeApiService.getVideos(playlistItem.getContentDetails().getVideoId());
                            videoListToAdd.add(videoList.get(0));
                        } catch (IOException e) {
                            throw new RuntimeException("Problem getting videos", e);
                        }
                    });
                    playlistVideos.setVideos(videoListToAdd);
                } catch (IOException e) {
                    throw new RuntimeException("Problem getting playlist items", e);
                }
                result.add(playlistVideos);
            });
        } catch (IOException e) {
            throw new RuntimeException("Problem getting playlists", e);
        }
        return ResponseEntity.ok().body(result);
    }
}
