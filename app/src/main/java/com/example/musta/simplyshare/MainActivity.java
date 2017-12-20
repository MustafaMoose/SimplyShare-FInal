package com.example.musta.simplyshare;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toolbar;
import android.view.View;

import com.example.musta.simplyshare.ApplicationTab.ApplicationModel;
import com.example.musta.simplyshare.MusicTab.MusicModel;
import com.example.musta.simplyshare.PicturesTab.PictureModel;
import com.example.musta.simplyshare.VideosTab.VideoModel;
import com.example.musta.simplyshare.fragments.ReceiveFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setActionBar(toolbar);
        checkPermissions();
    }


    //Enables the SendFiles button to go to the Select Files Page
    public void SelectFiles(View view) {
        Intent intent = new Intent(view.getContext(), SelectFiles.class);
        startActivity(intent);
    }

    //Enables the Receive button to go to the Receive Files Page
    public void ReceiveFiles(View view) {
        /*Intent intent = new Intent(MainActivity.this, ReceiveFiles.class);
        startActivity(intent);*/
        findViewById(R.id.container).setVisibility(View.VISIBLE);
        ReceiveFragment fragment = ReceiveFragment.newInstance();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container,fragment).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        findViewById(R.id.container).setVisibility(View.GONE);
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
            populateFiles();
            populatePictures();
            populateVideos();
            populateApplications();
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
            System.out.println("Source dir : " + packageInfo.publicSourceDir);
            System.out.println("Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
            File file = new File(packageInfo.publicSourceDir);
            float size = file.length();
            String ext = packageInfo.publicSourceDir;
            System.out.println(ext.substring(ext.lastIndexOf(".") + 1).trim());
            ext = ext.substring(ext.lastIndexOf(".") + 1).trim();
            int mask = ApplicationInfo.FLAG_SYSTEM | ApplicationInfo.FLAG_UPDATED_SYSTEM_APP;
            if((packageInfo.flags & mask) == 0) {
                ApplicationModel appModel = new ApplicationModel(packageInfo.packageName, String.valueOf(size), packageInfo.dataDir, ext, packageInfo.loadLabel(getPackageManager()).toString());
                applicationList.add(appModel);
            }
//            packageInfo.loadIcon(pm).
        }
        Collections.sort(applicationList, new Comparator<ApplicationModel>() {
            public int compare(ApplicationModel v1, ApplicationModel v2) {
                return v1.name.compareTo(v2.name);
            }
        });
        Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.PRIVATE).create();
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

