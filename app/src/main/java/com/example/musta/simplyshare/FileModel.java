package com.example.musta.simplyshare;

import android.graphics.Bitmap;

public class FileModel extends DataModel {

    public String size;
    public String path;
    public String date;
    public String iconLocation;

    public String toString() {
        return String.format("id: %d, Title: %s, Artist: %s, Path: %s, Date: %d, IconLocation %s",
                id, name, size, path, date, iconLocation);
    }

    public FileModel(String id, String name, String size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }
}
