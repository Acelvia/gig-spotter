package com.acelvia.settings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Settings {

    @Value("${zilyo.api.key}")
    private String zilyoApiKey;

    @Value("${indeed.api.key}")
    private String indeedApiKey;

    public String getZilyoApiKey() {
        return zilyoApiKey;
    }

    public String getIndeedApiKey() {
        return indeedApiKey;
    }
}
