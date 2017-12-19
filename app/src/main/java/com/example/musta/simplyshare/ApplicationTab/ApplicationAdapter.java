package com.example.musta.simplyshare.ApplicationTab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musta.simplyshare.R;
import com.example.musta.simplyshare.SendFiles;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by MA_Laptop on 11/5/2017.
 */

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ApplicationViewHolder> {

    ArrayList<ApplicationModel> mainData;
    HashMap<Integer, Boolean> selectedIndexes;
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
    private Context context;

    public ApplicationAdapter(ArrayList<ApplicationModel> mainData, HashMap<Integer, Boolean> selectedIndexes, Context context) {
        this.mainData = mainData;
        this.context = context;
        this.selectedIndexes = selectedIndexes;
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());

    }

    public ApplicationAdapter() {

    }

    @Override
    public ApplicationAdapter.ApplicationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_main, parent, false);

        v.setOnClickListener(ApplicationTab.appTabOnClickListener);
        return new ApplicationAdapter.ApplicationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ApplicationViewHolder holder, int position) {
        ImageView imageIcon = holder.imageIcon;
        TextView textName = holder.textName;
        TextView textInfo = holder.textInfo;

//        imageIcon.setImageDrawable(mainData.get(position).icon);
        if (mainData.get(position).name != null && !mainData.get(position).name.isEmpty())
            textName.setText(mainData.get(position).name);
        else
            textName.setText(mainData.get(position).packageName);
        float fileSize = Float.parseFloat(mainData.get(position).size);
        Drawable icon = null;
        try {
            icon = context.getPackageManager().getApplicationIcon(mainData.get(position).packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        imageIcon.setImageDrawable(icon);
        holder.bindSelection(position);
        fileSize = fileSize / (1024 * 1024);
        textInfo.setText(String.format("%.2f", fileSize) + " MB");
    }

    @Override
    public int getItemCount() {
        return mainData.size();
    }

    public HashMap<Integer, Boolean> saveSeletedIndexes(){
        return selectedIndexes;
    }

    public class ApplicationViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageIcon;
        public TextView textName;
        public TextView textInfo;
        private View mainView;
        public ApplicationViewHolder(View v) {
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

//    @Override
//    public void onItemClick(View view, int position) {
//        // check for item
//
//        view.setBackgroundColor(Color.parseColor("#eee"));
////        Intent intent = new Intent(mContext);
////        startActivity(intent);
//    }
//    private class AppTabOnClickListener implements android.view.View.OnClickListener {
//        private final Context context;
//
//        private AppTabOnClickListener(Context c) {
//            this.context = c;
//        }
//
//        @Override
//        public void onClick(View v) {
//            currentItem = recyclerView.getChildLayoutPosition(v);
//            startActivity(new Intent(getApplicationContext(), ));
//        }
//    }
}
