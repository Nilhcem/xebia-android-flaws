package com.example.mabanqueamoi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Collections;
import java.util.List;

public class AccountAdapter extends BaseAdapter {

    private List<BankEntry> mEntries = Collections.EMPTY_LIST;
    private final Context mContext;

    public AccountAdapter(Context context) {
        mContext = context;
    }

    public void updateEntries(List<BankEntry> entries) {
        mEntries = entries;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mEntries.size();
    }

    @Override
    public Object getItem(int position) {
        return mEntries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BankEntryView view;
        if (convertView == null) {
            view = new BankEntryView(mContext);
        } else {
            view = (BankEntryView) convertView;
        }
        view.showEntry(mEntries.get(position));
        return view;
    }
}
