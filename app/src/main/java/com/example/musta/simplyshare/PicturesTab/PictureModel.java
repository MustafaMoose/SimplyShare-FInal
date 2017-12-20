package com.example.musta.simplyshare.PicturesTab;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.musta.simplyshare.DataModel;

import java.io.Serializable;

/**
 * Created by MA_Laptop on 11/5/2017.
 */

public class PictureModel implements Serializable, Parcelable {

    public String id;
    public String name;
    public String ext;
    public byte[] data;
    public String IPAddress;
    public String size;
    public String path;
    public String date;
    public String iconLocation;

    public PictureModel() {
    }

    protected PictureModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        ext = in.readString();
        data = in.createByteArray();
        IPAddress = in.readString();
        size = in.readString();
        path = in.readString();
        date = in.readString();
        iconLocation = in.readString();
    }

    public static final Creator<PictureModel> CREATOR = new Creator<PictureModel>() {
        @Override
        public PictureModel createFromParcel(Parcel in) {
            return new PictureModel(in);
        }

        @Override
        public PictureModel[] newArray(int size) {
            return new PictureModel[size];
        }
    };

    public String toString() {
        return String.format("id: %d, Title: %s, Artist: %s, Path: %s, Date: %d, IconLocation %s",
                id, name, size, path, date, iconLocation);
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
        dest.writeString(size);
        dest.writeString(path);
        dest.writeString(date);
        dest.writeString(iconLocation);
    }
}
