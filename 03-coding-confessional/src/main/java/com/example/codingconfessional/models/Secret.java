package com.example.codingconfessional.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Secret {

    private String mAuthor;
    private String mContent;

    @JsonProperty("author")
    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    @JsonProperty("content")
    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
