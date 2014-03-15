package com.example.imageuploader.tasks;

import android.os.AsyncTask;
import android.util.Log;

public class LoadingTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... params) {
        Log.d(LoadingTask.class.getSimpleName(), "Loading...");
        return null;
    }
}
