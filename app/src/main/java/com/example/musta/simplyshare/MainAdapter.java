package com.example.musta.simplyshare;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by musta on 03-Nov-17.
 */

class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private ArrayList<MainDataDef> mainData;

    public MainAdapter(ArrayList<MainDataDef> mainData) {
        this.mainData = mainData;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_main, parent, false);

        v.setOnClickListener(ApplicationTab.appTabOnClickListener);
        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {

        ImageView imageIcon = holder.imageIcon;
        TextView textName = holder.textName;
        TextView textInfo = holder.textInfo;

        imageIcon.setImageResource(mainData.get(position).getImage());
        textName.setText(mainData.get(position).getName());
        textInfo.setText(mainData.get(position).getInfo());

    }

    @Override
    public int getItemCount() {
        return mainData.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageIcon;
        public TextView textName;
        public TextView textInfo;

        public MainViewHolder(View v) {
            super(v);
            this.imageIcon = (ImageView) v.findViewById(R.id.card_image);
            this.textName = (TextView) v.findViewById(R.id.card_name);
            this.textInfo = (TextView) v.findViewById(R.id.card_info);
        }
    }

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
