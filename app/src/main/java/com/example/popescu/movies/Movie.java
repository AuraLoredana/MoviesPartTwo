package com.example.popescu.movies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by POPESCU on 4/14/2018.
 */

public class Movie implements Parcelable {
    public String display_name;
    public float rating;
    public Double popularity;
    public String released_date;
    public String overview;
    public String poster_url;
    public int id;


    protected Movie(Parcel in) {
        display_name = in.readString();
        rating = in.readFloat();
        if (in.readByte() == 0) {
            popularity = null;
        } else {
            popularity = in.readDouble();
        }
        released_date = in.readString();
        overview = in.readString();
        poster_url = in.readString();
        id = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Movie() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(display_name);
        dest.writeFloat(rating);
        if (popularity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(popularity);
        }
        dest.writeString(released_date);
        dest.writeString(overview);
        dest.writeString(poster_url);
        dest.writeInt(id);
    }
}
