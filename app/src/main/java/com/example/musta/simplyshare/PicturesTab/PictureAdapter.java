package com.example.musta.simplyshare.PicturesTab;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
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
import com.example.musta.simplyshare.SendFiles;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by MA_Laptop on 11/5/2017.
 */

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {
    ArrayList<PictureModel> mainData;
    HashMap<Integer, Boolean> selectedIndexes;
    private Context context;

    public PictureAdapter(ArrayList<PictureModel> mainData, HashMap<Integer, Boolean> selectedIndexes, Context context) {
        this.mainData = mainData;
        this.context = context;
        this.selectedIndexes = selectedIndexes;
    }

    @Override
    public PictureAdapter.PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_main, parent, false);

//        v.setOnClickListener(ApplicationTab.appTabOnClickListener);
        return new PictureAdapter.PictureViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {
        ImageView imageIcon = holder.imageIcon;
        TextView textName = holder.textName;
        TextView textInfo = holder.textInfo;

//        imageIcon.setImageURI(MediaStore.Images.Thumbnails.getThumbnail());
        if(mainData.get(position).name.length() > 54)
            textName.setText(mainData.get(position).name.substring(0,54));
        else
            textName.setText(mainData.get(position).name);
        float fileSize = Float.parseFloat(mainData.get(position).size);
        fileSize = fileSize/(1024*1024);
        textInfo.setText(String.format("%.2f", fileSize) + " MB");
        holder.bindSelection(position);
    }

    public HashMap<Integer, Boolean> saveSeletedIndexes(){
        return selectedIndexes;
    }

    @Override
    public int getItemCount() {
        return mainData.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageIcon;
        public TextView textName;
        public TextView textInfo;
        private View mainView;

        public PictureViewHolder(View v) {
            super(v);
            mainView = v;
            this.imageIcon = (ImageView) v.findViewById(R.id.card_image);
            this.textName = (TextView) v.findViewById(R.id.card_name);
            this.textInfo = (TextView) v.findViewById(R.id.card_info);

        }

        public void bindSelection(final int index){
            if(selectedIndexes.containsKey(index) && selectedIndexes.get(index)){
                mainView.setBackgroundColor(context.getResources().getColor(android.R.color.holo_blue_bright));
            }else{
                selectedIndexes.put(index, false);
                mainView.setBackgroundColor(context.getResources().getColor(android.R.color.white));
            }
            mainView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(selectedIndexes.containsKey(index) && !selectedIndexes.get(index)){
                        mainView.setBackgroundColor(context.getResources().getColor(android.R.color.holo_blue_bright));
                        notifyItemChanged(index);
                        selectedIndexes.put(index, true);
                    }else{
                        mainView.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                        notifyItemChanged(index);
                        selectedIndexes.put(index, false);
                    }
                }
            });
        }
    }
}
