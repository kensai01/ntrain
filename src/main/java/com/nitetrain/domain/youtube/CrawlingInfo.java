package com.nitetrain.domain.youtube;

import java.util.Date;

public class CrawlingInfo extends BaseEntity {

    private String searchKey;

    private String currentPageToken;

    private String nextPageToken;

    private long totalCount;

    public CrawlingInfo() {}

    public CrawlingInfo(
        long id,
        String createdBy,
        String updatedBy,
        Date created,
        Date updated,
        String searchKey,
        String currentPageToken,
        String nextPageToken,
        long totalCount
    ) {
        super(id, createdBy, updatedBy, created, updated);
        this.searchKey = searchKey;
        this.currentPageToken = currentPageToken;
        this.nextPageToken = nextPageToken;
        this.totalCount = totalCount;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getCurrentPageToken() {
        return currentPageToken;
    }

    public void setCurrentPageToken(String currentPageToken) {
        this.currentPageToken = currentPageToken;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
