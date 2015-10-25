package com.acelvia.client.zilyomodel;

import java.util.List;

/**
 * Defines the common fields of all responses from Zilyo.
 */
public abstract class ZilyoResponse {

    private List<String> ids;
    private int status;
    private int page;
    private int resultsPerPage;

    public ZilyoResponse(List<String> ids, int status, int page, int resultsPerPage) {
        this.ids = ids;
        this.status = status;
        this.page = page;
        this.resultsPerPage = resultsPerPage;
    }

    public List<String> getIds() {
        return ids;
    }

    public int getStatus() {
        return status;
    }

    public int getPage() {
        return page;
    }

    public int getResultsPerPage() {
        return resultsPerPage;
    }
}
