package com.example.musta.simplyshare.ApplicationTab;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ApplicationTab extends Fragment {
    public static View.OnClickListener appTabOnClickListener;
    RecyclerView recyclerView;
    ArrayList<ApplicationTab> applicationData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        appTabOnClickListener = new AppTabOnClickListener(this.getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Declare your first fragment herecheckPermissions();
        View view = inflater.inflate(R.layout.select_apps, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        Gson gson = new Gson();
        String json = sharedPrefs.getString("applicationList", null);
        Type type = new TypeToken<ArrayList<ApplicationModel>>() {
        }.getType();
        ArrayList<ApplicationModel> applicationList = gson.fromJson(json, type);
        RecyclerView.Adapter adapter = new ApplicationAdapter(applicationList);
        recyclerView.setAdapter(adapter);
//        recyclerView.setOnItem
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

}