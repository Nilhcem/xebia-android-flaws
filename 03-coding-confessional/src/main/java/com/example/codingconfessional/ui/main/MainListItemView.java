package com.example.codingconfessional.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.codingconfessional.R;
import com.example.codingconfessional.models.Secret;

public class MainListItemView extends LinearLayout {

    private TextView mAuthor;
    private TextView mDescription;

    public MainListItemView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.item, this, true);
        mAuthor = (TextView) findViewById(R.id.author);
        mDescription = (TextView) findViewById(R.id.description);
    }

    public void bindData(Secret secret) {
        mAuthor.setText(secret.getAuthor());
        mDescription.setText(secret.getContent());
    }
}
