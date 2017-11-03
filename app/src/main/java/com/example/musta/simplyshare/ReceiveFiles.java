package com.example.musta.simplyshare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ReceiveFiles extends AppCompatActivity {
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.receive);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Creates a back button
        }

        //Activates the back button function
        @Override
        public boolean onSupportNavigateUp(){
            finish();
            return true;
        }
    }
