package com.example.eltobgy.teleprompterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.eltobgy.teleprompterapp.activities.TeleprompterMainActivities.TeleprompterActivity;
import com.example.eltobgy.teleprompterapp.utlis.Helper;

/**
 * Created by Eltobgy on 12-Jul-18.
 */

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Helper.showLog(TAG, "onCreate() Splash:" + 1);
        Intent intent = new Intent(this, TeleprompterActivity.class);
        startActivity(intent);
        finish();
    }
}

