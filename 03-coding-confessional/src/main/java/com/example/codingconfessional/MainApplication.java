package com.example.codingconfessional;

import android.app.Application;
import android.util.Log;
import com.example.codingconfessional.web.retrofit.JacksonConverter;
import com.example.codingconfessional.web.retrofit.KeystoreConfiguration;
import com.example.codingconfessional.web.retrofit.OkHttpClientFactory;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.path.android.jobqueue.log.CustomLogger;
import com.squareup.okhttp.OkHttpClient;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

import java.util.HashMap;
import java.util.Map;

public class MainApplication extends Application {

    private static final String TAG = MainApplication.class.getSimpleName();
    private static final String KEYSTORE_PWD = "keystore";
    private static final String SERVICE_ENDPOINT = "https://example.org";

    private static MainApplication sInstance;

    private RestAdapter mRestAdapter;
    private JobManager mJobManager;
    private final Map<Class<?>, Object> mRetrofitServiceMap = new HashMap<Class<?>, Object>();

    public MainApplication() {
        sInstance = this;
    }

    public static MainApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        configureRetrofit();
        configureJobManager();
    }

    private void configureRetrofit() {
        KeystoreConfiguration config = new KeystoreConfiguration(R.raw.keystore, KEYSTORE_PWD);
        OkHttpClient okHttpClient = null;

        try {
            okHttpClient = OkHttpClientFactory.getInstance().newSecuredHttpClient(this, config);
        } catch (Exception e) {
            Log.e(TAG, "Failed to create Secured Http Client", e);
        }

        RestAdapter.Builder builder = new RestAdapter.Builder();
        if (okHttpClient != null) {
            builder.setClient(new OkClient(okHttpClient));
        }

        mRestAdapter = builder
                .setConverter(new JacksonConverter())
                .setEndpoint(SERVICE_ENDPOINT)
                .build();
    }

    private void configureJobManager() {
        Configuration configuration = new Configuration.Builder(this)
                .customLogger(new CustomLogger() {
                    @Override
                    public boolean isDebugEnabled() {
                        return true;
                    }

                    @Override
                    public void d(String text, Object... args) {
                        Log.d(TAG, String.format(text, args));
                    }

                    @Override
                    public void e(Throwable t, String text, Object... args) {
                        Log.e(TAG, String.format(text, args), t);
                    }

                    @Override
                    public void e(String text, Object... args) {
                        Log.e(TAG, String.format(text, args));
                    }
                })
                .minConsumerCount(1)//always keep at least one consumer alive
                .maxConsumerCount(3)//up to 3 consumers at a time
                .loadFactor(3)//3 jobs per consumer
                .consumerKeepAlive(120)//wait 2 minute
                .build();
        mJobManager = new JobManager(this, configuration);
    }

    public JobManager getJobManager() {
        return mJobManager;
    }

    @SuppressWarnings("unchecked")
    public <T> T getRetrofitService(Class<T> serviceClass) {
        synchronized (mRetrofitServiceMap) {
            T service = (T) mRetrofitServiceMap.get(serviceClass);
            if (service == null) {
                service = mRestAdapter.create(serviceClass);
                mRetrofitServiceMap.put(serviceClass, service);
            }
            return service;
        }
    }
}
