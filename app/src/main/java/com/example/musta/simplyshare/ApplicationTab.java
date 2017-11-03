package com.example.musta.simplyshare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ApplicationTab extends Fragment {
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Declare your first fragment here
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getView().getContext());

        ArrayList<MainDataDef> mainData = new ArrayList<>();
        for(int i = 0; i < MainData.nameArray.length; i++) {
            mainData.add(new MainDataDef(
                    MainData.imageArray[i],
                    MainData.nameArray[i],
                    MainData.infoArray[i]
            ));
        }

        RecyclerView.Adapter adapter = new MainAdapter(mainData);

        return inflater.inflate(R.layout.select_apps, container, false);
    }



}