package com.example.photopaypaypay;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DLCDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "PhotoPayPayPay.db";
    private static final int DATABASE_VERSION = 42;

    public DLCDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
