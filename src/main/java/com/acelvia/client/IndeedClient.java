package com.acelvia.client;

import com.acelvia.client.indeedmodel.Response;
import com.acelvia.client.indeedmodel.Result;
import com.acelvia.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
public class IndeedClient {

    @Autowired
    private Settings settings;

    private static String INDEED_ENDPOINT = "http://api.indeed.com/ads/apisearch";

    public List<Result> Get(String query, String city) {
        System.out.println(query+" - "+ city);

        Response response = ClientBuilder.newClient()
                .target(INDEED_ENDPOINT)
                .queryParam("publisher", settings.getIndeedApiKey())
                .queryParam("v", 2)
                .queryParam("q", query)
                .queryParam("l", city)
                .request(MediaType.APPLICATION_XML_TYPE)
                .get(Response.class);

        return response.getResults();
    }
}
