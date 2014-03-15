package com.example.codingconfessional.events;

import com.example.codingconfessional.models.Secrets;

public final class FetchedSecretsEvent {

    public final Secrets secrets;

    public FetchedSecretsEvent(Secrets fetchedSecrets) {
        secrets = fetchedSecrets;
    }
}
