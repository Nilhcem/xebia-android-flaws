package com.example.photopaypaypay;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements ActionBar.OnNavigationListener {

    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selectedItem";
    private static final String[] DROPDOWN_VALUES = new String[]{"Effects", "Lovely", "Glitter", "VanGogh"};
    private static final int[] DROPDOWN_EFFECTS = new int[]{0, R.drawable.lovely, R.drawable.glitter, R.drawable.vangogh};
    private static final String DATABASE_TABLE = "PhotoPayDLC";
    private static final String[] DATABASE_EFFECTS = new String[]{"lovely_effect", "glitter_effect", "vangogh_effect"};

    private ImageView mEffect;
    private boolean[] mHasPaid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mEffect = (ImageView) findViewById(R.id.effect);
        loadDatabaseStuff();
        initActionBar();
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        if (mHasPaid[itemPosition]) {
            mEffect.setImageResource(DROPDOWN_EFFECTS[itemPosition]);
        } else {
            Toast.makeText(this, "Please buy the $0.99 IAP", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void loadDatabaseStuff() {
        int idx = 0;
        mHasPaid = new boolean[DROPDOWN_EFFECTS.length];
        mHasPaid[idx++] = true;

        DLCDatabase db = new DLCDatabase(this);
        Cursor cursor = db.getReadableDatabase().query(DATABASE_TABLE, DATABASE_EFFECTS, null, null, null, null, null);
        cursor.moveToFirst();
        for (String effect : DATABASE_EFFECTS) {
            mHasPaid[idx++] = cursor.getInt(cursor.getColumnIndex(effect)) != 0;
        }
        cursor.close();
    }

    private void initActionBar() {
        ActionBar actionbar = getActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(actionbar.getThemedContext(), android.R.layout.simple_spinner_item, android.R.id.text1, DROPDOWN_VALUES);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        actionbar.setListNavigationCallbacks(adapter, this);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar().getSelectedNavigationIndex());
    }
}
