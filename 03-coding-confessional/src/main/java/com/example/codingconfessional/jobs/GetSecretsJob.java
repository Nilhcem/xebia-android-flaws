package com.example.codingconfessional.jobs;

import com.example.codingconfessional.MainApplication;
import com.example.codingconfessional.events.FetchedSecretsEvent;
import com.example.codingconfessional.events.NetworkErrorEvent;
import com.example.codingconfessional.models.Secret;
import com.example.codingconfessional.models.Secrets;
import com.example.codingconfessional.utils.DecryptUtils;
import com.example.codingconfessional.web.api.SecretsService;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;
import de.greenrobot.event.EventBus;

public class GetSecretsJob extends Job {

    public GetSecretsJob() {
        super(new Params(Priority.MIDDLE).setRequiresNetwork(false));
    }

    @Override
    public void onAdded() {
    }

    @Override
    public void onRun() throws Throwable {
        SecretsService service = MainApplication.getInstance().getRetrofitService(SecretsService.class);
        Secrets secrets = service.getLatestSecrets();
        for (Secret secret : secrets.getSecrets()) {
            secret.setAuthor(DecryptUtils.decrypt(secret.getAuthor()));
            secret.setContent(DecryptUtils.decrypt(secret.getContent()));
        }
        EventBus.getDefault().post(new FetchedSecretsEvent(secrets));
    }

    @Override
    protected void onCancel() {
        EventBus.getDefault().post(new NetworkErrorEvent());
    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        return false;
    }
}
