package com.example.musta.simplyshare;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

/**
 * Created by MA_Laptop on 11/5/2017.
 */

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 7000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.splash);
//        ProgressBar spinner;
//        spinner = (ProgressBar)findViewById(R.id.pbHeaderProgress);
//        spinner.setVisibility(View.VISIBLE);
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish();
//        spinner.setVisibility(View.GONE);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        };
        new Handler().postDelayed(runnable, 500);
//        runnable.run();
//        new Handler().postDelayed(new Runnable() {
//
//            /*
//             * Showing splash screen with a timer. This will be useful when you
//             * want to show case your app logo / company
//             */
//
//            @Override
//            public void run() {
//                // This method will be executed once the timer is over
//                // Start your app main activity
//
//                // close this activity
//                finish();
//            }
//        }, SPLASH_TIME_OUT);
    }
}
