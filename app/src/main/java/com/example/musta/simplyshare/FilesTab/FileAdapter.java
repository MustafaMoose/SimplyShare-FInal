package com.example.musta.simplyshare.FilesTab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musta.simplyshare.ApplicationTab.ApplicationTab;
import com.example.musta.simplyshare.MusicTab.MusicAdapter;
import com.example.musta.simplyshare.MusicTab.MusicModel;
import com.example.musta.simplyshare.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by MA_Laptop on 11/5/2017.
 */

public class FileAdapter  extends RecyclerView.Adapter<FileAdapter.FileViewHolder> {

    ArrayList<FileModel> mainData;

    public FileAdapter(ArrayList<FileModel> mainData) {
        this.mainData = mainData;
    }

    public FileAdapter() {

    }

    @Override
    public FileAdapter.FileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_main, parent, false);

        v.setOnClickListener(ApplicationTab.appTabOnClickListener);
        return new FileAdapter.FileViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FileAdapter.FileViewHolder holder, int position) {
        ImageView imageIcon = holder.imageIcon;
        TextView textName = holder.textName;
        TextView textInfo = holder.textInfo;

        imageIcon.setImageResource(R.mipmap.ic_file);
        if(mainData.get(position).name.length() > 54)
            textName.setText(mainData.get(position).name.substring(0,54));
        else
            textName.setText(mainData.get(position).name);
        float fileSize = Float.parseFloat(mainData.get(position).size);
        fileSize = fileSize/(1024*1024);
        textInfo.setText(String.format("%.2f", fileSize) + " MB");
    }

    @Override
    public int getItemCount() {
        return mainData.size();
    }

    public class FileViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageIcon;
        public TextView textName;
        public TextView textInfo;

        public FileViewHolder(View v) {
            super(v);
            this.imageIcon = (ImageView) v.findViewById(R.id.card_image);
            this.textName = (TextView) v.findViewById(R.id.card_name);
            this.textInfo = (TextView) v.findViewById(R.id.card_info);
        }
    }
}
