package com.example.imageuploader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.example.imageuploader.tasks.UploadTask;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DoSomeStuff.computeNumbers(12, 32);
    }

    public void onButtonClick(View button) {
        new UploadTask(this).execute();
    }
}
