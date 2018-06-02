package com.example.popescu.movies.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by POPESCU on 4/15/2018.
 */

public class DBContract {
    public static final String AUTHORITY = "com.example.popescu.movies";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);


    public DBContract() {
    }

    public static final class MovieEntry implements BaseColumns {
        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_NAME = "display_name";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_RELEASE = "released_date";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_POSTER = "poster_url";
        public static final String COLUMN_ID = "_id";
    }
}
