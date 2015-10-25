package com.acelvia.client.zilyomodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * A response sent by Zilio to an accommodation search.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccommodationResponse extends ZilyoResponse {

    private List<Listing> listings;

    @JsonCreator
    public AccommodationResponse(@JsonProperty("ids") List<String> ids,
                                 @JsonProperty("status") int status,
                                 @JsonProperty("page") int page,
                                 @JsonProperty("resultsPerPage") int resultsPerPage,
                                 @JsonProperty("result") List<Listing> listings) {
        super(ids, status, page, resultsPerPage);
        this.listings = listings;
    }

    public List<Listing> getListings() {
        return listings;
    }
}
