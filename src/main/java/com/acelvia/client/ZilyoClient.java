package com.acelvia.client;

import com.acelvia.client.zilyomodel.AccommodationResponse;
import com.acelvia.client.zilyomodel.CountResponse;
import com.acelvia.client.zilyomodel.Listing;
import com.acelvia.client.zilyomodel.ZilyoResponse;
import com.acelvia.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Service
public class ZilyoClient {

    @Autowired
    private Settings settings;

    @Autowired
    private Client client;

    private static final String ZILYO_SEARCH_ENDPOINT = "https://zilyo.p.mashape.com/search";
    private static final String ZILYO_COUNT_ENDPOINT = "https://zilyo.p.mashape.com/count";

    public AccommodationResponse fetchListings(float latitude, float longitude) {
        return appendMetaDataAndExecute(client.target(ZILYO_SEARCH_ENDPOINT)
                .queryParam("latitude", String.valueOf(latitude))
                .queryParam("longitude", String.valueOf(longitude))
                .queryParam("resultsperpage", 50), AccommodationResponse.class);
    }

    public AccommodationResponse fetchListings(float northEastLatitude, float northEastLongitude,
                                               float southWestLatitude, float southWestLongitude) {
        CountResponse count = getResultCount(northEastLatitude, northEastLongitude,
                southWestLatitude, southWestLongitude);
        List<String> allIds = new ArrayList<>();
        List<Listing> allListings = new ArrayList<>();

        for (int page = 0; page < count.getTotalPages(); page++) {
            AccommodationResponse response = appendMetaDataAndExecute(client.target(ZILYO_SEARCH_ENDPOINT)
                            .queryParam("nelatitude", northEastLatitude)
                            .queryParam("nelongitude", northEastLongitude)
                            .queryParam("swlatitude", southWestLatitude)
                            .queryParam("swlongitude", southWestLongitude)
                            .queryParam("resultsperpage", 50)
                            .queryParam("page", page), AccommodationResponse.class);
            allIds.addAll(response.getIds());
            allListings.addAll(response.getListings());
        }

        return new AccommodationResponse(allIds, 200, 1, count.getTotalResults(), allListings);
    }

    private CountResponse getResultCount(float northEastLatitude, float northEastLongitude,
                                         float southWestLatitude, float southWestLongitude) {
        return appendMetaDataAndExecute(client.target(ZILYO_COUNT_ENDPOINT)
                .queryParam("nelatitude", northEastLatitude)
                .queryParam("nelongitude", northEastLongitude)
                .queryParam("swlatitude", southWestLatitude)
                .queryParam("swlongitude", southWestLongitude), CountResponse.class);
    }

    private <T extends ZilyoResponse> T appendMetaDataAndExecute(WebTarget target, Class<T> resultType) {
        return target.request().header("X-Mashape-Key", settings.getZilyoApiKey())
                .accept(MediaType.APPLICATION_JSON_TYPE).get(resultType);
    }
}
