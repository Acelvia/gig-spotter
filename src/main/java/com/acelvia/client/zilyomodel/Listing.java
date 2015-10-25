package com.acelvia.client.zilyomodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a single accommodation listing in Zilyo.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Listing {

    /** The id assigned to the listing by Zilyo */
    private String id;
    private Float latitude;
    private Float longitude;

    @JsonCreator
    public Listing(@JsonProperty("id") String id, @JsonProperty("latLng") float[] latLng) {
        this.id = id;
        if (latLng != null && latLng.length == 2) {
            this.latitude = latLng[0];
            this.longitude = latLng[1];
        }
    }

    public String getId() {
        return id;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }
}
