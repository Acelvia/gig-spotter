package com.acelvia.controller;

import com.acelvia.client.IndeedClient;
import com.acelvia.client.indeedmodel.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobsController {

    @RequestMapping("/jobs")
    @ResponseBody
    List<Result> jobs() {
        return new IndeedClient().Get("");
    }
}
