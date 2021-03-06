package com.example.musta.simplyshare.FilesTab;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musta.simplyshare.*;
import com.example.musta.simplyshare.ApplicationTab.ApplicationModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class FileTab extends Fragment {
    RecyclerView recyclerView;
    private FileAdapter adapter;
    ArrayList<FileModel> fileList;
    private HashMap<Integer, Boolean> selectedIndexes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedIndexes = new HashMap<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_apps, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        Gson gson = new Gson();
        String json = sharedPrefs.getString("fileList", null);
        Type type = new TypeToken<ArrayList<FileModel>>() {
        }.getType();

        fileList = gson.fromJson(json, type);
        adapter = new FileAdapter(fileList, selectedIndexes, getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }

    public ArrayList<FileModel> getSelectedItems(){
        ArrayList<FileModel> selectedItems = new ArrayList<>();
        if(selectedIndexes != null && !selectedIndexes.isEmpty()) {
            for (Integer i : selectedIndexes.keySet()) {
                selectedItems.add(this.fileList.get(i));
            }
        }
        return selectedItems;
    }

    public void saveSelectedIndexes(){
        if(adapter != null ){
            this.selectedIndexes = adapter.saveSeletedIndexes();
            Log.d("MMMM", "saveSelectedIndexes: ");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        saveSelectedIndexes();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        saveSelectedIndexes();
    }

    @Override
    public void onPause() {
        super.onPause();
        saveSelectedIndexes();
    }

}