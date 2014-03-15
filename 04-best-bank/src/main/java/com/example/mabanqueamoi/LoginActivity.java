package com.example.mabanqueamoi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import static android.view.WindowManager.LayoutParams.FLAG_SECURE;

public class LoginActivity extends Activity {

    private static final int REQUEST_ACCOUNT = 42;
    private static final String PREFS_ACCOUNT_NB = "accountNb";
    private static final String PREFS_PASSWORD = "password";

    private EditText mAccountNb;
    private EditText mPassword;
    private CheckBox mRememberMe;
    private SharedPreferences mSharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(FLAG_SECURE, FLAG_SECURE);
        mSharedPrefs = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        setContentView(R.layout.login);
        mAccountNb = (EditText) findViewById(R.id.account_number);
        mPassword = (EditText) findViewById(R.id.password);
        mRememberMe = (CheckBox) findViewById(R.id.remember_me);
        fillRememberMeData();
    }

    public void openAccount(View button) {
        if (TextUtils.isEmpty(mAccountNb.getText()) || TextUtils.isEmpty(mPassword.getText())) {
            Toast.makeText(this, "Invalid data", Toast.LENGTH_SHORT).show();
        } else {
            saveRememberMeData();
            Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
            intent.putExtra(AccountActivity.EXTRA_ACCOUNT_NB, mAccountNb.getText().toString());
            startActivityForResult(intent, REQUEST_ACCOUNT);
        }
    }

    private void fillRememberMeData() {
        String accountNb = mSharedPrefs.getString(PREFS_ACCOUNT_NB, null);
        if (!TextUtils.isEmpty(accountNb)) {
            mAccountNb.setText(accountNb);
        }

        String password = mSharedPrefs.getString(PREFS_PASSWORD, null);
        if (!TextUtils.isEmpty(password)) {
            mPassword.setText(password);
        }
    }

    private void saveRememberMeData() {
        String accountNb = null;
        String password = null;

        if (mRememberMe.isChecked()) {
            accountNb = mAccountNb.getText().toString();
            password = mPassword.getText().toString();
        }

        mSharedPrefs.edit()
                .putString(PREFS_ACCOUNT_NB, accountNb)
                .putString(PREFS_PASSWORD, password)
                .commit();
    }
}
