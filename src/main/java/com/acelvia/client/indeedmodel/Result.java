package com.acelvia.client.indeedmodel;

import javax.xml.bind.annotation.XmlElement;

public class Result {
    private String jobtitle;
    private String url;

    public String getJobtitle() {
        return jobtitle;
    }

    @XmlElement
    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getUrl() {
        return url;
    }

    @XmlElement
    public void setUrl(String url) {
        this.url = url;
    }
}
