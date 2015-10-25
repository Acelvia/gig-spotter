package com.acelvia.controller;

import com.acelvia.client.IndeedClient;
import com.acelvia.client.indeedmodel.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
public class JobsController {

    @Autowired
    private IndeedClient indeedClient;

    @RequestMapping(value = "/jobs", produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    List<Result> jobs(@QueryParam("city") String city, 
                      @QueryParam("q") String q) {
        return indeedClient.Get(q, city);
    }
}
