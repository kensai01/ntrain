package com.nitetrain.service.impl;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import com.google.api.services.youtube.model.*;
import com.nitetrain.service.YouTubeApiService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class YouTubeApiServiceImpl implements YouTubeApiService {

    @Autowired
    private Environment env;

    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    public static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private YouTube youtube;

    public YouTubeApiServiceImpl() {}

    public void setYouTubeService() {
        this.youtube =
            new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, request -> {})
                .setApplicationName("Nitetrain")
                .setYouTubeRequestInitializer(new YouTubeRequestInitializer(env.getProperty("youtube.apikey")))
                .build();
    }

    public List<Playlist> getPlaylistsForChannelId(String channelId) throws IOException {
        if (this.youtube == null) {
            this.setYouTubeService();
        }
        YouTube.Playlists.List playlists = youtube.playlists().list("snippet");
        playlists.setChannelId(channelId);
        PlaylistListResponse playlistListResponse = playlists.execute();
        List<Playlist> p = playlistListResponse.getItems();
        return p;
    }

    public List<PlaylistItem> getPlaylistItems(String playlistId) throws IOException {
        YouTube.PlaylistItems.List playlistItems = youtube.playlistItems().list("contentDetails");
        playlistItems.setPlaylistId(playlistId);
        PlaylistItemListResponse playlistItemListResponse = playlistItems.execute();
        List<PlaylistItem> pi = playlistItemListResponse.getItems();
        return pi;
    }

    public List<Video> getVideos(String videoId) throws IOException {
        YouTube.Videos.List videos = youtube.videos().list("snippet, contentDetails");
        videos.setId(videoId);
        VideoListResponse videoListResponse = videos.execute();
        List<Video> v = videoListResponse.getItems();
        return v;
    }
}
