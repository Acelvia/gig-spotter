package com.acelvia.client;

import org.junit.Assert;
import org.junit.Test;

public class ZilyoClientTest {

    @Test
    public void findListingsAroundCoordinates_returnsSomethingForHelsinki() {
        float latitudeHelsinki = 60.1733343f,
                longitudeHelsinki = 24.93227f;
        String result = new ZilyoClient()
                .fetchListings(latitudeHelsinki, longitudeHelsinki);
        Assert.assertNotNull(result);
    }
}
