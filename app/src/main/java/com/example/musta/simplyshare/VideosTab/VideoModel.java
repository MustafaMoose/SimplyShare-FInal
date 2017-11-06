package com.example.musta.simplyshare.VideosTab;

/**
 * Created by MA_Laptop on 11/5/2017.
 */

public class VideoModel {

    public String id;
    public String name;
    public String size;
    public String path;
    public String date;
    public String iconLocation;

    public String toString() {
        return String.format("id: %d, Title: %s, Artist: %s, Path: %s, Date: %d, IconLocation %s",
                id, name, size, path, date, iconLocation);
    }
}
