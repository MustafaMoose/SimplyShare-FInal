package com.example.musta.simplyshare.ApplicationTab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musta.simplyshare.R;
import com.example.musta.simplyshare.SendFiles;

import java.util.ArrayList;

/**
 * Created by MA_Laptop on 11/5/2017.
 */

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ApplicationViewHolder> {

    ArrayList<ApplicationModel> mainData;

    public ApplicationAdapter(ArrayList<ApplicationModel> mainData) {
        this.mainData = mainData;
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
        if (mainData.get(position).packageName.length() > 54)
            textName.setText(mainData.get(position).packageName.substring(0, 54));
        else
            textName.setText(mainData.get(position).packageName);
        float fileSize = Float.parseFloat(mainData.get(position).size);
        fileSize = fileSize / (1024 * 1024);
        textInfo.setText(String.format("%.2f", fileSize) + " MB");
    }

    @Override
    public int getItemCount() {
        return mainData.size();
    }

    public class ApplicationViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageIcon;
        public TextView textName;
        public TextView textInfo;

        public ApplicationViewHolder(View v) {
            super(v);
            this.imageIcon = (ImageView) v.findViewById(R.id.card_image);
            this.textName = (TextView) v.findViewById(R.id.card_name);
            this.textInfo = (TextView) v.findViewById(R.id.card_info);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();
                    System.out.println(mainData.get(pos));
//                    v.setBackgroundColor(Color.BLUE);
                    // check if item still exists
//                    if(pos != RecyclerView.NO_POSITION){
//                        RvDataItem clickedDataItem = dataItems.get(pos);
//                        Toast.makeText(v.getContext(), "You clicked " + mainData.get(pos).packageName, Toast.LENGTH_SHORT).show();

                    Bundle b = new Bundle();
//                        b.putSerializable("sendingObject", mainData.get(pos));
                    Intent intent = new Intent(v.getContext(), SendFiles.class);
                    intent.putExtra("sendingObject", mainData.get(pos));
                    v.getContext().startActivity(intent);
//                    }
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
