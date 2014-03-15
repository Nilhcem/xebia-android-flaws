package com.example.codingconfessional.ui.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.example.codingconfessional.models.Secret;

import java.util.Collections;
import java.util.List;

public class MainListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Secret> mSecrets = Collections.EMPTY_LIST;

    public MainListAdapter(Context context) {
        mContext = context;
    }

    public void updateData(List<Secret> secrets) {
        mSecrets = secrets;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mSecrets.size();
    }

    @Override
    public Object getItem(int position) {
        return mSecrets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MainListItemView view;
        if (convertView == null) {
            view = new MainListItemView(mContext);
        } else {
            view = (MainListItemView) convertView;
        }
        view.bindData(mSecrets.get(position));
        return view;
    }
}
