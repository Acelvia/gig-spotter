package com.acelvia.client;

import com.acelvia.client.zilyomodel.AccommodationResponse;
import com.acelvia.settings.Settings;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Service
public class ZilyoClient {

    @Autowired
    private Settings settings;

    private static final String ZILYO_ENDPOINT = "https://zilyo.p.mashape.com/search";

    public AccommodationResponse fetchListings(float latitude, float longitude) {
        return appendMetaDataAndExecute(client().target(ZILYO_ENDPOINT)
                .queryParam("latitude", String.valueOf(latitude))
                .queryParam("longitude", String.valueOf(longitude))
                .queryParam("resultsperpage", 50));
    }

    public AccommodationResponse fetchListings(float northEastLatitude, float northEastLongitude,
                                float southWestLatitude, float southWestLongitude) {
        return appendMetaDataAndExecute(client().target(ZILYO_ENDPOINT)
                .queryParam("nelatitude", northEastLatitude)
                .queryParam("nelongitude", northEastLongitude)
                .queryParam("swlatitude", southWestLatitude)
                .queryParam("swlongitude", southWestLongitude)
                .queryParam("resultsperpage", 50));
    }

    private static Client client() {
        return ClientBuilder.newClient(new ClientConfig(JacksonFeature.class));
    }

    private AccommodationResponse appendMetaDataAndExecute(WebTarget target) {
        return target.request().header("X-Mashape-Key", settings.getZilyoApiKey())
                .accept(MediaType.APPLICATION_JSON_TYPE).get(AccommodationResponse.class);
    }
}
