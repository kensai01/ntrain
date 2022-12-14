package com.nitetrain.domain.youtube;

import java.util.Date;

public class YouTubeVideoStatistics extends BaseEntity {

    private long likeCount;

    private long dislikeCount;

    private long viewCount;

    private long favouriteCount;

    private long commentCount;

    private String videoId;

    public YouTubeVideoStatistics() {}

    public YouTubeVideoStatistics(
        long id,
        String createdBy,
        String updatedBy,
        Date created,
        Date updated,
        long likeCount,
        long dislikeCount,
        long viewCount,
        long favouriteCount,
        long commentCount,
        String videoId
    ) {
        super(id, createdBy, updatedBy, created, updated);
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.viewCount = viewCount;
        this.favouriteCount = favouriteCount;
        this.commentCount = commentCount;
        this.videoId = videoId;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public long getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(long dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public long getFavouriteCount() {
        return favouriteCount;
    }

    public void setFavouriteCount(long favouriteCount) {
        this.favouriteCount = favouriteCount;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
