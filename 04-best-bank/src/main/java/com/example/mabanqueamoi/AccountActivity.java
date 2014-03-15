package com.example.mabanqueamoi;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.view.WindowManager.LayoutParams.FLAG_SECURE;

public class AccountActivity extends Activity {

    public static final String EXTRA_ACCOUNT_NB = "accountNb";

    private AccountAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(FLAG_SECURE, FLAG_SECURE);
        ListView view = new ListView(this);
        view.setBackgroundColor(0xffffffff);
        mAdapter = new AccountAdapter(this);
        mAdapter.updateEntries(getEntries());
        view.setAdapter(mAdapter);
        view.setDivider(new ColorDrawable(0xffc9c9c9));
        view.setDividerHeight(1);
        setContentView(view);
    }

    private List<BankEntry> getEntries() {
        List<BankEntry> entries = new ArrayList<BankEntry>();
        entries.add(new BankEntry("DAB 09/03 11H08 FRANCONVILLE EP", "11/03/2014", "-300,00", false));
        entries.add(new BankEntry("PRLV SEPA RECU DD29P", "10/03/2014", "-47,80", false));
        entries.add(new BankEntry("CB LEADER PRICE 08/03", "08/03/2014", "-31,55", false));
        entries.add(new BankEntry("COTIS CONV AVENIR", "06/03/2014", "-3,80", false));
        entries.add(new BankEntry("CB GOOGLE *Google Play 04/03", "05/03/2014", "-358,99", false));
        entries.add(new BankEntry("CB LIDL 4242 03/03", "04/03/2014", "-108,91", false));
        entries.add(new BankEntry("CB GAL.LAFAYETTE 28/02", "28/02/2014", "-12,80", false));
        entries.add(new BankEntry("VIR XEBIA IT ARCHI V84F3", "28/02/2014", "+98 734,42", true));
        entries.add(new BankEntry("CB CENTRE E.LECLERC", "27/02/2014", "-128,19", false));
        entries.add(new BankEntry("CB SNCF 25/02", "25/02/2014", "-105,40", false));
        return entries;
    }
}
