package com.example.eltobgy.teleprompterapp.activities.TeleprompterMainActivities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.eltobgy.teleprompterapp.R;
import com.example.eltobgy.teleprompterapp.utlis.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eltobgy on 11-Jul-18.
 */

public class SettingsActivity extends AppCompatActivity {
    private static final String TAG = SettingsFragment.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.backArrow)
    ImageView backArrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);



        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.showLog(TAG, "onClick: navigating back to 'Teleprompter Activity'");
                finish();
            }
        });
        init();
    }
    private void init(){
        Helper.showLog(TAG, "init: inflating " + getString(R.string.SettingsFragment));

        SettingsFragment fragment = new SettingsFragment();
        FragmentTransaction transaction = SettingsActivity.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(getString(R.string.SettingsFragment));
        transaction.commit();
    }
}