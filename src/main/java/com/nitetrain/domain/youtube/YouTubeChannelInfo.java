package com.nitetrain.domain.youtube;

import java.util.Date;

public class YouTubeChannelInfo extends BaseEntity {

    private String channelId;

    private String name;

    private long subscriptionCount;

    public YouTubeChannelInfo() {}

    public YouTubeChannelInfo(
        long id,
        String createdBy,
        String updatedBy,
        Date created,
        Date updated,
        String channelId,
        String name,
        long subscriptionCount
    ) {
        super(id, createdBy, updatedBy, created, updated);
        this.channelId = channelId;
        this.name = name;
        this.subscriptionCount = subscriptionCount;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSubscriptionCount() {
        return subscriptionCount;
    }

    public void setSubscriptionCount(long subscriptionCount) {
        this.subscriptionCount = subscriptionCount;
    }
}
