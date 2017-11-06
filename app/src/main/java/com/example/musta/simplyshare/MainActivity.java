package com.example.musta.simplyshare;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.musta.simplyshare.ApplicationTab.ApplicationModel;
import com.example.musta.simplyshare.MusicTab.MusicModel;
import com.example.musta.simplyshare.PicturesTab.PictureModel;
import com.example.musta.simplyshare.VideosTab.VideoModel;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<MusicModel> musicData;
    ArrayList<FileModel> fileData;
    ArrayList<ApplicationModel> applicationModel;
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
    private int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        editor = sharedPrefs.edit();
        checkPermissions();
    }


    //Enables the Send button to go to the Select Files Page
    public void SelectFiles(View view) {
        Intent intent = new Intent(view.getContext(), SelectFiles.class);
        startActivity(intent);
    }

    //Enables the Receive button to go to the Receive Files Page
    public void ReceiveFiles(View view) {
        Intent intent = new Intent(MainActivity.this, ReceiveFiles.class);
        startActivity(intent);
    }

    public void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            System.out.println("PERMISSION GRANTED");
            // Should we show an explanation?
//            if (shouldShowRequestPermissionRationale(
//                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                // Explain to the user why we need to read the contacts
//            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique
        } else {
            System.out.println("PERMISSION ALREADY EXISTS");
            populateMusic();
            populateApplications();
            populateFiles();
            populatePictures();
            populateVideos();
        }
    }

    public void populateMusic() {
        ArrayList<MusicModel> musicList = PopulateData.getMusicInfo(this.getApplicationContext());
        Gson gson = new Gson();
        String json = gson.toJson(musicList);
        editor.putString("musicList", json);
        editor.commit();
    }

    public void populateFiles() {
        ArrayList<FileModel> musicList = PopulateData.getFileInfo(this.getApplicationContext());
        Gson gson = new Gson();
        String json = gson.toJson(musicList);
        editor.putString("fileList", json);
        editor.commit();
    }

    public void populateVideos() {
        ArrayList<VideoModel> videoList = PopulateData.getVideoInfo(this.getApplicationContext());
        Gson gson = new Gson();
        String json = gson.toJson(videoList);
        editor.putString("videoList", json);
        editor.commit();
    }

    public void populatePictures() {
        ArrayList<PictureModel> pictureList = PopulateData.getPicutreInfo(this.getApplicationContext());
        Gson gson = new Gson();
        String json = gson.toJson(pictureList);
        editor.putString("pictureList", json);
        editor.commit();
    }

    public void populateApplications() {
        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        ArrayList<ApplicationModel> applicationList = new ArrayList<ApplicationModel>();
        for (ApplicationInfo packageInfo : packages) {
            System.out.println("Installed package :" + packageInfo.loadLabel(getPackageManager()).toString());
            System.out.println("Source dir : " + packageInfo.sourceDir);
            System.out.println("Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
            File file = new File(packageInfo.publicSourceDir);
            float size = file.length();
            applicationList.add(new ApplicationModel(packageInfo.loadLabel(getPackageManager()).toString(), String.valueOf(size)));
//            packageInfo.loadIcon(pm).
        }
        Gson gson = new Gson();
        String json = gson.toJson(applicationList);
        editor.putString("applicationList", json);
        editor.commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        populateMusic();
        populateApplications();
        populateFiles();
        populatePictures();
        populateVideos();
    }

}

