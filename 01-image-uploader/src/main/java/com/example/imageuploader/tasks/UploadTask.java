package com.example.imageuploader.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class UploadTask extends AsyncTask<Void, Void, Void> {

    private static final String FTP_HOST = "imgs.example.org";
    private static final String FTP_PASSWORD = "f0rTheW1n";
    private static final int FTP_PORT = 666;
    private static final String FTP_USERNAME = "ftpandroid";

    private Context mContext;

    public UploadTask(Context context) {
        mContext = context.getApplicationContext();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(mContext, "Ready to upload", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.i(UploadTask.class.getSimpleName(), "Ready to upload images to " + FTP_HOST);

        // Do some complicated stuff here
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(mContext, "Upload finished", Toast.LENGTH_SHORT).show();
    }
}
