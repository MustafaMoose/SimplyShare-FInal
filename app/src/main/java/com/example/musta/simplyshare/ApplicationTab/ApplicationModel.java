package com.example.musta.simplyshare.ApplicationTab;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.musta.simplyshare.DataModel;
import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by MA_Laptop on 11/5/2017.
 */

public class ApplicationModel implements Serializable, Parcelable{

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


    public ApplicationModel() {
    }

    public ApplicationModel(String packageName, String size, String path, String ext, String name) {
        this.packageName = packageName;
        this.size = size;
        this.path = path;
        this.ext = ext;
        this.name = name;
//        this.icon = icon;
    }

    protected ApplicationModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        ext = in.readString();
        data = in.createByteArray();
        IPAddress = in.readString();
        packageName = in.readString();
        size = in.readString();
        path = in.readString();
    }

    public static final Creator<ApplicationModel> CREATOR = new Creator<ApplicationModel>() {
        @Override
        public ApplicationModel createFromParcel(Parcel in) {
            return new ApplicationModel(in);
        }

        @Override
        public ApplicationModel[] newArray(int size) {
            return new ApplicationModel[size];
        }
    };

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(ext);
        dest.writeByteArray(data);
        dest.writeString(IPAddress);
        dest.writeString(packageName);
        dest.writeString(size);
        dest.writeString(path);
    }
}
