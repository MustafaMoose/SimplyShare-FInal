package com.example.musta.simplyshare.ApplicationTab;

import android.graphics.drawable.Drawable;

import com.example.musta.simplyshare.DataModel;
import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by MA_Laptop on 11/5/2017.
 */

public class ApplicationModel implements Serializable{

    public String id;
    public String name;
    public String ext;
    public byte[] data;
    public String IPAddress;
    public String packageName;
    public String size;
    public String path;
    @Expose
    private Drawable icon;

//    public String toString() {
//        return String.format("id: %d, Title: %s, Artist: %s, Path: %s, Date: %d, IconLocation %s",
//                id, name, size, path, date, iconLocation);
//    }

    public ApplicationModel(String packageName, String size, String path, String ext, String name) {
        this.packageName = packageName;
        this.size = size;
        this.path = path;
        this.ext = ext;
        this.name = name;
//        this.icon = icon;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
