package com.lamusica.lamusicavideo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by @pablopantaleon on 2/19/16.
 */
public class Video implements Parcelable {

    public String name;
    public String url;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.url);
    }

    public Video() {
    }

    protected Video(Parcel in) {
        this.name = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Video> CREATOR = new Parcelable.Creator<Video>() {
        public Video createFromParcel(Parcel source) {
            return new Video(source);
        }

        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
}
