package com.example.musta.simplyshare.VideosTab;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musta.simplyshare.PicturesTab.PictureAdapter;
import com.example.musta.simplyshare.PicturesTab.PictureModel;
import com.example.musta.simplyshare.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class VideoTab extends Fragment {
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_apps, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        Gson gson = new Gson();
        String json = sharedPrefs.getString("videoList", null);
        Type type = new TypeToken<ArrayList<VideoModel>>() {
        }.getType();
        ArrayList<VideoModel> videoList = gson.fromJson(json, type);
        RecyclerView.Adapter adapter = new VideoAdapter(videoList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}