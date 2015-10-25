package com.acelvia.controller;

import com.acelvia.client.ZilyoClient;
import com.acelvia.client.zilyomodel.AccommodationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Controller
@EnableAutoConfiguration
@ComponentScan("com.acelvia")
public class MainController {

    @Autowired
    private ZilyoClient zilyoClient;

    /**
     * Searches for accommodations based on the given coordinates.
     *
     * @return The results as a JSON string. The format of the JSON is
     * defined <a href="https://market.mashape.com/zilyo/zilyo">here</a>.
     * <p>
     * There are two different searches that can be made with this method:
     * <ul>
     * <li>
     * If {@code lat} and {@code lng} are both provided, a "point
     * search" will be made, where accommodations will be searched
     * for near the given location.
     * </li>
     * <li>
     * If {@code neLat}, {@code neLng}, {@code swLat} and {@code swLng}
     * are all provided, a "box search" will be made, where
     * accommodations will be searched for in the square defined
     * by the given two points (i.e. the northeast and southwest
     * corners of the area).
     * </li>
     * </ul>
     */
    @ResponseBody
    @RequestMapping(value = "/accommodation", produces = MediaType.APPLICATION_JSON)
    public AccommodationResponse findAccommodations(@QueryParam("lat") String lat,
                                                    @QueryParam("lng") String lng,
                                                    @QueryParam("neLat") String neLat,
                                                    @QueryParam("neLng") String neLng,
                                                    @QueryParam("swLat") String swLat,
                                                    @QueryParam("swLng") String swLng) {
        if (notEmpty(lat, lng)) {
            return zilyoClient.fetchListings(new Float(lat), new Float(lng));
        } else if (notEmpty(neLat, neLng, swLat, swLng)) {
            return zilyoClient.fetchListings(new Float(neLat), new Float(neLng),
                    new Float(swLat), new Float(swLng));
        }
        throw new IllegalArgumentException("Coordinates missing from the request");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MainController.class, args);
    }

    private static boolean notEmpty(String... strings) {
        for (String string : strings) {
            if (string == null || string.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
