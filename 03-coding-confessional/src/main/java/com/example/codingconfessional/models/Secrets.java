package com.example.codingconfessional.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Secrets {

    private List<Secret> mSecrets;

    @JsonProperty("secrets")
    public List<Secret> getSecrets() {
        return mSecrets;
    }

    public void setSecrets(List<Secret> secrets) {
        mSecrets = secrets;
    }
}
