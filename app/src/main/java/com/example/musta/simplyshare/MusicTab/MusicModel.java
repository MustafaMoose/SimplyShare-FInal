package com.example.musta.simplyshare.MusicTab;

import com.example.musta.simplyshare.DataModel;

import java.io.Serializable;

/**
 * Created by MA_Laptop on 11/5/2017.
 */

public class MusicModel implements Serializable{

    public String id;
    public String name;
    public String ext;
    public byte[] data;
    public String IPAddress;
    public String size;
    public String path;
    public String date;
    public String iconLocation;

    public String toString() {
        return String.format("id: %d, Title: %s, Artist: %s, Path: %s, Date: %d, IconLocation %s",
                id, name, size, path, date, iconLocation);
    }
}
