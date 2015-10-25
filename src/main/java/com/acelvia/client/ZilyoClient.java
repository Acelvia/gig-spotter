package com.acelvia.client;

import com.acelvia.client.zilyomodel.AccommodationResponse;
import com.acelvia.client.zilyomodel.CountResponse;
import com.acelvia.client.zilyomodel.Listing;
import com.acelvia.client.zilyomodel.ZilyoResponse;
import com.acelvia.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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

    public AccommodationResponse fetchListings(float northEastLatitude,
                                               float northEastLongitude,
                                               float southWestLatitude,
                                               float southWestLongitude) throws APIUsageException {
        CountResponse count = getResultCount(northEastLatitude, northEastLongitude,
                southWestLatitude, southWestLongitude);
        List<String> allIds = new ArrayList<>();
        List<Listing> allListings = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(count.getTotalPages());

        for (int page = 0; page < count.getTotalPages(); page++) {
            client.target(ZILYO_SEARCH_ENDPOINT)
                    .queryParam("nelatitude", northEastLatitude)
                    .queryParam("nelongitude", northEastLongitude)
                    .queryParam("swlatitude", southWestLatitude)
                    .queryParam("swlongitude", southWestLongitude)
                    .queryParam("resultsperpage", 50)
                    .queryParam("page", page)
                    .request().accept(MediaType.APPLICATION_JSON_TYPE)
                    .header("X-Mashape-Key", settings.getZilyoApiKey())
                    .async()
                    .get(new InvocationCallback<AccommodationResponse>() {
                        @Override
                        public void completed(AccommodationResponse accommodationResponse) {
                            allIds.addAll(accommodationResponse.getIds());
                            allListings.addAll(accommodationResponse.getListings());
                            latch.countDown();
                        }

                        @Override
                        public void failed(Throwable throwable) {
                            // TODO: Report this to the user somehow / abort the whole search?
                            throwable.printStackTrace();
                            latch.countDown();
                        }
                    });
        }

        try {
            if (!latch.await(15, TimeUnit.SECONDS)) {
                throw new APIUsageException("Accommodation listing search did not complete in a reasonable time");
            }
        } catch (InterruptedException e) {
            throw new APIUsageException("An error occurred while fetching accommodation listings", e);
        }

        // Build an aggregate response object from all of the individual responses
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
