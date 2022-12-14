package com.nitetrain.domain.youtube;

import com.google.api.services.youtube.model.Video;
import java.util.List;

public class PlaylistVideos {

    String playlistTitle;
    String description;
    List<Video> videos;

    public PlaylistVideos(String playlistTitle, String description, List<Video> videos) {
        this.playlistTitle = playlistTitle;
        this.description = description;
        this.videos = videos;
    }

    public PlaylistVideos() {}

    public String getPlaylistTitle() {
        return playlistTitle;
    }

    public void setPlaylistTitle(String playlistTitle) {
        this.playlistTitle = playlistTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
