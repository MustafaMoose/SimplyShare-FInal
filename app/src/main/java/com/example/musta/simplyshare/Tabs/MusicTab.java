package com.example.musta.simplyshare.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.musta.simplyshare.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MusicTab extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Declare your second fragment here
        return inflater.inflate(R.layout.select_music, container, false);

    }

}