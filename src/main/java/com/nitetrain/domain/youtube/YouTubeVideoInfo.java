package com.nitetrain.domain.youtube;

import java.util.Date;

public class YouTubeVideoInfo extends BaseEntity {

    private String videoId;

    private String title;

    private String thumbnailUrl;

    private String description;

    private Date publishedDate;

    private String videoDefinition;

    private String videoDuration;

    private String videoCaption;

    private String videoprojection;

    private String countryRestricted;

    private String keyword;

    private YouTubeChannelInfo channelInfo;

    private YouTubeVideoStatistics videoStatistics;

    public YouTubeVideoInfo(
        long id,
        String createdBy,
        String updatedBy,
        Date created,
        Date updated,
        String videoId,
        String title,
        String thumbnailUrl,
        String description,
        Date publishedDate,
        String videoDefinition,
        String videoDuration,
        String videoCaption,
        String videoprojection,
        String countryRestricted,
        String keyword,
        YouTubeChannelInfo channelInfo,
        YouTubeVideoStatistics videoStatistics
    ) {
        super(id, createdBy, updatedBy, created, updated);
        this.videoId = videoId;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
        this.publishedDate = publishedDate;
        this.videoDefinition = videoDefinition;
        this.videoDuration = videoDuration;
        this.videoCaption = videoCaption;
        this.videoprojection = videoprojection;
        this.countryRestricted = countryRestricted;
        this.keyword = keyword;
        this.channelInfo = channelInfo;
        this.videoStatistics = videoStatistics;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getVideoDefinition() {
        return videoDefinition;
    }

    public void setVideoDefinition(String videoDefinition) {
        this.videoDefinition = videoDefinition;
    }

    public String getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }

    public String getVideoCaption() {
        return videoCaption;
    }

    public void setVideoCaption(String videoCaption) {
        this.videoCaption = videoCaption;
    }

    public String getVideoprojection() {
        return videoprojection;
    }

    public void setVideoprojection(String videoprojection) {
        this.videoprojection = videoprojection;
    }

    public String getCountryRestricted() {
        return countryRestricted;
    }

    public void setCountryRestricted(String countryRestricted) {
        this.countryRestricted = countryRestricted;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public YouTubeChannelInfo getChannelInfo() {
        return channelInfo;
    }

    public void setChannelInfo(YouTubeChannelInfo channelInfo) {
        this.channelInfo = channelInfo;
    }

    public YouTubeVideoStatistics getVideoStatistics() {
        return videoStatistics;
    }

    public void setVideoStatistics(YouTubeVideoStatistics videoStatistics) {
        this.videoStatistics = videoStatistics;
    }
}
