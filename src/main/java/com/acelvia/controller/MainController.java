package com.acelvia.controller;

import com.acelvia.client.ZilyoClient;
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
@ComponentScan("com.acelvia")
@EnableAutoConfiguration
public class MainController {

    @Autowired
    private ZilyoClient zilyoClient;

    @RequestMapping(value = "/accommodation", produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public String home(@QueryParam("latitude") String latitude,
                       @QueryParam("longitude") String longitude) {
        return zilyoClient.fetchListings(new Float(latitude), new Float(longitude));
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MainController.class, args);
    }
}
