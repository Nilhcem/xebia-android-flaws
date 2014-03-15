package com.example.codingconfessional.ui.main;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.example.codingconfessional.MainApplication;
import com.example.codingconfessional.R;
import com.example.codingconfessional.events.FetchedSecretsEvent;
import com.example.codingconfessional.events.NetworkErrorEvent;
import com.example.codingconfessional.jobs.GetSecretsJob;
import com.path.android.jobqueue.JobManager;
import de.greenrobot.event.EventBus;

public class MainActivity extends Activity {

    private EventBus mEventBus = EventBus.getDefault();
    private JobManager mJobManager;
    private MainListAdapter mAdapter;
    private ListView mMainLayout;
    private View mErrorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mMainLayout = (ListView) findViewById(R.id.main_layout);
        mErrorLayout = findViewById(R.id.error_layout);

        mAdapter = new MainListAdapter(this);
        mMainLayout.setAdapter(mAdapter);
        mJobManager = MainApplication.getInstance().getJobManager();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mEventBus.register(this);
        mJobManager.addJobInBackground(new GetSecretsJob());
    }

    @Override
    protected void onPause() {
        try {
            EventBus.getDefault().unregister(this);
        } catch (Throwable t) {
            //this may crash if registration did not go through. just be safe
        }
        super.onPause();
    }

    public void onEventMainThread(FetchedSecretsEvent event) {
        mAdapter.updateData(event.secrets.getSecrets());
        toggleLayouts(false);
    }

    public void onEventMainThread(NetworkErrorEvent event) {
        toggleLayouts(true);
    }

    public void onRetryButtonClicked(View view) {
        toggleLayouts(false);
        mJobManager.addJobInBackground(new GetSecretsJob());
    }

    private void toggleLayouts(boolean hasError) {
        if (hasError) {
            mMainLayout.setVisibility(View.GONE);
            mErrorLayout.setVisibility(View.VISIBLE);
        } else {
            mMainLayout.setVisibility(View.VISIBLE);
            mErrorLayout.setVisibility(View.GONE);
        }
    }
}
