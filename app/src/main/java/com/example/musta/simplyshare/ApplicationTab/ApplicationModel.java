package com.example.musta.simplyshare.ApplicationTab;

import android.graphics.drawable.Drawable;

/**
 * Created by MA_Laptop on 11/5/2017.
 */

public class ApplicationModel {

    public String id;
    public String packageName;
    public String size;
    public String path;
    public String date;
    public Drawable icon;

//    public String toString() {
//        return String.format("id: %d, Title: %s, Artist: %s, Path: %s, Date: %d, IconLocation %s",
//                id, name, size, path, date, iconLocation);
//    }

    public ApplicationModel(String packageName, String size) {
        this.packageName = packageName;
        this.size = size;
//        this.icon = icon;
    }
}
