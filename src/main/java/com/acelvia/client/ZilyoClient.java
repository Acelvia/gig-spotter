package com.acelvia.client;

import com.acelvia.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@Service
public class ZilyoClient {

    @Autowired
    private Settings settings;

    private static final String ZILYO_ENDPOINT = "https://zilyo.p.mashape.com/search";

    public String fetchListings(float latitude, float longitude) {
        return ClientBuilder.newClient().target(ZILYO_ENDPOINT)
                .queryParam("latitude", String.valueOf(latitude))
                .queryParam("longitude", String.valueOf(longitude))
                .request().header("X-Mashape-Key", settings.getZilyoApiKey())
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(String.class);
    }

    public String fetchListings(float northEastLatitude, float northEastLongitude,
                                float southWestLatitude, float southWestLongitude) {
        return ClientBuilder.newClient().target(ZILYO_ENDPOINT)
                .queryParam("nelatitude", northEastLatitude)
                .queryParam("nelongitude", northEastLongitude)
                .queryParam("swlatitude", southWestLatitude)
                .queryParam("swlongitude", southWestLongitude)
                .request().header("X-Mashape-Key", settings.getZilyoApiKey())
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(String.class);
    }


}
