package com.example.musta.simplyshare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ApplicationTab extends Fragment {
    static  View.OnClickListener appTabOnClickListener;
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appTabOnClickListener = new AppTabOnClickListener(this.getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Declare your first fragment here
        View view =  inflater.inflate(R.layout.select_apps, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<MainDataDef> mainData = new ArrayList<>();
        for(int i = 0; i < MainData.nameArray.length; i++) {
            mainData.add(new MainDataDef(
                    MainData.imageArray[i],
                    MainData.nameArray[i],
                    MainData.infoArray[i]
            ));
        }

        RecyclerView.Adapter adapter = new MainAdapter(mainData);
        recyclerView.setAdapter(adapter);

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