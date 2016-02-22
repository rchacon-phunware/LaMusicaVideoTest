package com.lamusica.lamusicavideo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by @pablopantaleon on 2/19/16.
 */
public class Video implements Parcelable {

    public String name;
    public String url;

    public boolean showAd;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeByte(showAd ? (byte) 1 : (byte) 0);
    }

    public Video() {
    }

    protected Video(Parcel in) {
        this.name = in.readString();
        this.url = in.readString();
        this.showAd = in.readByte() != 0;
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        public Video createFromParcel(Parcel source) {
            return new Video(source);
        }

        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
}
