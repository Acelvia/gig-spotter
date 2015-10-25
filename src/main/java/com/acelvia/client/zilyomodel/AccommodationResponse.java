package com.acelvia.client.zilyomodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * A response sent by Zilio to an accommodation search.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccommodationResponse {

    private List<String> ids;
    private int status;
    private int page;
    private int resultsPerPage;
    private List<Listing> listings;

    @JsonCreator
    public AccommodationResponse(@JsonProperty("ids") List<String> ids,
                                 @JsonProperty("status") int status,
                                 @JsonProperty("page") int page,
                                 @JsonProperty("resultsperpage") int resultsPerPage,
                                 @JsonProperty("result") List<Listing> listings) {
        this.ids = ids;
        this.status = status;
        this.page = page;
        this.resultsPerPage = resultsPerPage;
        this.listings = listings;
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

    public List<Listing> getListings() {
        return listings;
    }
}
