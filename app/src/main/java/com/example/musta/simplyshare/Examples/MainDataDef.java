package com.example.musta.simplyshare.Examples;

/**
 * Created by musta on 03-Nov-17.
 */

public class MainDataDef {
    int image;
    String name;
    String info;

    public MainDataDef(int image, String name, String info) {
        this.image = image;
        this.name = name;
        this.info = info;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

}
