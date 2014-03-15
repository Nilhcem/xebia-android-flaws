package com.example.mabanqueamoi;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BankEntryView extends RelativeLayout {

    private TextView mAmount;
    private TextView mTitle;
    private TextView mDate;

    public BankEntryView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.entry, this, true);
        mAmount = (TextView) findViewById(R.id.amount);
        mTitle = (TextView) findViewById(R.id.name);
        mDate = (TextView) findViewById(R.id.date);
    }

    public void showEntry(BankEntry entry) {
        mTitle.setText(entry.title);
        mDate.setText(entry.date);
        mAmount.setText(entry.amount);
        mAmount.setTextColor(entry.isCredit ? 0xff00792e : 0xff9d0000);
    }
}
