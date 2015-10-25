package com.acelvia.client.zilyomodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * A response sent by Zilyo for a count query (i.e. how many total results
 * does a search for accommodations with a set of parameters have).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountResponse extends ZilyoResponse {

    private Count count;

    @JsonCreator
    public CountResponse(@JsonProperty("ids") List<String> ids,
                         @JsonProperty("status") int status,
                         @JsonProperty("page") int page,
                         @JsonProperty("resultsPerPage") int resultsPerPage,
                         @JsonProperty("result") Count count) {
        super(ids, status, page, resultsPerPage);
        this.count = count;
    }

    public Integer getTotalResults() {
        return count != null ? count.getTotalResults() : null;
    }

    public Integer getTotalPages() {
        return count != null ? count.getTotalPages() : null;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Count {

        private Integer totalResults;
        private Integer totalPages;

        @JsonCreator
        public Count(@JsonProperty("totalResults") Integer totalResults,
                     @JsonProperty("totalPages") Integer totalPages) {
            this.totalResults = totalResults;
            this.totalPages = totalPages;
        }

        public Integer getTotalResults() {
            return totalResults;
        }

        public Integer getTotalPages() {
            return totalPages;
        }
    }
}
