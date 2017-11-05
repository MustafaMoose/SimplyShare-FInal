package com.example.musta.simplyshare.Tabs;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musta.simplyshare.AppTabDetail;
import com.example.musta.simplyshare.FileModel;
import com.example.musta.simplyshare.MainAdapter;
import com.example.musta.simplyshare.PopulateData;
import com.example.musta.simplyshare.R;

import java.util.ArrayList;

public class ApplicationTab extends Fragment {
    private static int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE;
    public static  View.OnClickListener appTabOnClickListener;
    RecyclerView recyclerView;
    ArrayList<FileModel> musicData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appTabOnClickListener = new AppTabOnClickListener(this.getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Declare your first fragment herecheckPermissions();
        View view =  inflater.inflate(R.layout.select_apps, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

//        ArrayList<MainDataDef> mainData = new ArrayList<>();
//        for(int i = 0; i < MainData.nameArray.length; i++) {
//            mainData.add(new MainDataDef(
//                    MainData.imageArray[i],
//                    MainData.nameArray[i],
//                    MainData.infoArray[i]
//            ));
//        }
        checkPermissions();

        return view;
    }

    class AppTabOnClickListener implements android.view.View.OnClickListener {
        private final Context context;

        private AppTabOnClickListener(Context c) {
            this.context = c;
        }

        @Override
        public void onClick(View v) {
            int currentItem = recyclerView.getChildLayoutPosition(v);
            startActivity(new Intent(v.getContext(), AppTabDetail.class));
//            LayoutInflater.from(v.getContext()).inflate(R.layout.apptabdetails);
        }
    }

    public void populateData() {

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        musicData = populateMusic();
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//
//                } else {
//                    System.out.println("PERMISSION DENIED BWHAHAHA");
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
//                return;
//            }
//
//            // other 'case' lines to check for other
//            // permissions this app might request
//        }
    }

    public ArrayList<FileModel> populateMusic() {
        System.out.println("PERMISSION GRANTED");
        ArrayList<FileModel> musiclist = PopulateData.getMusicInfos(this.getContext());
        RecyclerView.Adapter adapter = new MainAdapter(musiclist);
        recyclerView.setAdapter(adapter);
        return musiclist;
//        for(int i = 0; i < musiclist.size() ; i++) {
//            System.out.println(musiclist.get(i).songTitle);
//        }


    }

    public void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

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
            musicData = populateMusic();
        }
    }



}