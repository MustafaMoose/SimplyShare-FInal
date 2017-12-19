package com.example.musta.simplyshare.ApplicationTab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musta.simplyshare.AppTabDetail;
import com.example.musta.simplyshare.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class ApplicationTab extends Fragment {
    public static View.OnClickListener appTabOnClickListener;
    RecyclerView recyclerView;
    ArrayList<ApplicationTab> applicationData;
    private ApplicationAdapter adapter;
    private HashMap<Integer, Boolean> selectedIndexes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedIndexes = new HashMap<>();
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
        Log.d("FFFF", "onCreateView: "+applicationList.toString());
        adapter = new ApplicationAdapter(applicationList, selectedIndexes, getContext());
        recyclerView.setAdapter(adapter);
//        recyclerView.setOnItem
        return view;
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