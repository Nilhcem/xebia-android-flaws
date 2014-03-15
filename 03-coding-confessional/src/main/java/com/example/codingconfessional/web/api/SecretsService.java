package com.example.codingconfessional.web.api;

import com.example.codingconfessional.models.Secrets;
import retrofit.http.GET;

public interface SecretsService {
    @GET("/latest")
    Secrets getLatestSecrets();
}
