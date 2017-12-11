package com.example.musta.simplyshare.VideosTab;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musta.simplyshare.ApplicationTab.ApplicationAdapter;
import com.example.musta.simplyshare.ApplicationTab.ApplicationTab;
import com.example.musta.simplyshare.FileModel;
import com.example.musta.simplyshare.MusicTab.MusicAdapter;
import com.example.musta.simplyshare.R;
import com.example.musta.simplyshare.SendFiles;

import java.util.ArrayList;

/**
 * Created by MA_Laptop on 11/5/2017.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    ArrayList<VideoModel> mainData;

    public VideoAdapter(ArrayList<VideoModel> mainData) {
        this.mainData = mainData;
    }

    public VideoAdapter() {

    }

    @Override
    public VideoAdapter.VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_main, parent, false);

//        v.setOnClickListener(ApplicationTab.appTabOnClickListener);
        return new VideoAdapter.VideoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        ImageView imageIcon = holder.imageIcon;
        TextView textName = holder.textName;
        TextView textInfo = holder.textInfo;

        imageIcon.setImageResource(R.drawable.simplyshare);
//        MediaStore.Video.Thumbnails.get
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

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageIcon;
        public TextView textName;
        public TextView textInfo;

        public VideoViewHolder(View v) {
            super(v);
            this.imageIcon = (ImageView) v.findViewById(R.id.card_image);
            this.textName = (TextView) v.findViewById(R.id.card_name);
            this.textInfo = (TextView) v.findViewById(R.id.card_info);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(), SendFiles.class);
                    intent.putExtra("sendingObject", mainData.get(pos));
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
