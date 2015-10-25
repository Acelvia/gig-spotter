package com.acelvia.client.zilyomodel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AccommodationResponseSerializationTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void resultIsDeserializedProperly() throws Exception {
        StringBuilder json = new StringBuilder();
        for (String line : Files.readAllLines(Paths.get("src", "test", "resources", "zilyo_example.json"))) {
            json.append(line);
        }
        AccommodationResponse accommodationResponse = mapper.readValue(json.toString(), AccommodationResponse.class);

        assertNotNull("The result items were null", accommodationResponse.getListings());
        assertEquals("Invalid amount of result items", 3, accommodationResponse.getListings().size());
        for (Listing listing : accommodationResponse.getListings()) {
            assertNotNull("Latitude was missing from a result", listing.getLatitude());
            assertNotNull("Longitude was missing from a result", listing.getLongitude());
        }
    }

    @Test
    public void resultItemListIsSerializedAndDeserializedProperly() throws Exception {
        List<Listing> originalValues = Arrays.asList(
                new Listing("air3290124", new float[]{60.173780606838f, 24.932198924238f}),
                new Listing("air1339771", new float[]{60.173674914635f, 24.929999522797f}),
                new Listing("air2085395", new float[]{60.172421811195f, 24.934932062549f})
        );
        String json = mapper.writeValueAsString(originalValues);
        List<Listing> deserialized = mapper.readValue(json, new TypeReference<List<Listing>>() {});

        for (int i = 0; i < originalValues.size(); i++) {
            assertEquals("The ids did not match between the original and the deserialized version",
                    originalValues.get(i).getId(), deserialized.get(i).getId());
            assertEquals("The latitudes did not match between the original and the deserialized version",
                    originalValues.get(i).getLatitude(), deserialized.get(i).getLatitude());
            assertEquals("The longitudes did not match between the original and the deserialized version",
                    originalValues.get(i).getLongitude(), deserialized.get(i).getLongitude());
        }
    }
}
