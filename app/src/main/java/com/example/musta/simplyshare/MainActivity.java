package com.example.musta.simplyshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
    }


    //Enables the Send button to go to the Select Files Page
    public void SelectFiles(View view) {
        Intent intent = new Intent(MainActivity.this, SelectFiles.class);
        startActivity(intent);
    }

    //Enables the Receive button to go to the Receive Files Page
    public void ReceiveFiles(View view) {
        Intent intent = new Intent(MainActivity.this, ReceiveFiles.class);
        startActivity(intent);
    }

}

