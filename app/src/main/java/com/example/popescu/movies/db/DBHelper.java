package com.example.popescu.movies.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by POPESCU on 4/15/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "movies.db";
    static final int DATABASE_VERSION = 1;

    public DBHelper(Context c){
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_TABLE = "create table " +  DBContract.MovieEntry.TABLE_NAME + " (" +
                DBContract.MovieEntry.COLUMN_ID + " INTEGER PRIMARY KEY, " +
                DBContract.MovieEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                DBContract.MovieEntry.COLUMN_OVERVIEW + " TEXT NOT NULL, " +
                DBContract.MovieEntry.COLUMN_POSTER + " TEXT NOT NULL, " +
                DBContract.MovieEntry.COLUMN_RATING + " TEXT NOT NULL, " +
                DBContract.MovieEntry.COLUMN_RELEASE + " TEXT NOT NULL" +
                ")";
        Log.d("TABLE", "creating table " + CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +  DBContract.MovieEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}