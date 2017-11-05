package com.example.musta.simplyshare;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musta.simplyshare.Tabs.ApplicationTab;

import java.util.ArrayList;

/**
 * Created by musta on 03-Nov-17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

//    private ArrayList<MainDataDef> mainData;
    ArrayList<FileModel> mainData;

    public MainAdapter(ArrayList<FileModel> mainData) {
        this.mainData = mainData;
    }

    public MainAdapter() {

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

        imageIcon.setImageResource(R.drawable.simplyshare);
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
