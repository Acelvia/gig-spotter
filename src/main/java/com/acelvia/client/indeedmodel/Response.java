package com.acelvia.client.indeedmodel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Response {

    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    @XmlElementWrapper(name = "results")
    @XmlElement(name = "result")
    public void setResults(List<Result> results) {
        this.results = results;
    }
}
