package com.example.popescu.movies.db;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by POPESCU on 4/15/2018.
 */

public class MovieProvider extends ContentProvider {
    private static final int MOVIE_DETAIL = 2;
    private static final int MOVIE_LIST = 1;
    static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(DBContract.AUTHORITY, "movies", MOVIE_LIST);
        sUriMatcher.addURI(DBContract.AUTHORITY, "#", MOVIE_DETAIL);
    }

    static String LOG_TAG = "Database";
    DBHelper DBHelper;
    SQLiteDatabase database;

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (sUriMatcher.match(uri)){
            case MOVIE_LIST: {
                count = database.delete(DBContract.MovieEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            case MOVIE_DETAIL: {
                count = database.delete(DBContract.MovieEntry.TABLE_NAME, DBContract.MovieEntry.COLUMN_ID + " = ?", selectionArgs);
                break;
            }
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri returnUri;
        long _id = database.insert(DBContract.MovieEntry.TABLE_NAME, null, values);
        Log.v(LOG_TAG, "Inserted - id = " + _id);
        if (_id > 0) {
            returnUri = ContentUris.withAppendedId(DBContract.CONTENT_URI, _id);
            getContext().getContentResolver().notifyChange(returnUri, null);
            return returnUri;
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        DBHelper = new DBHelper(getContext());
        database = DBHelper.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        if (sortOrder == null) sortOrder = DBContract.MovieEntry.COLUMN_ID;
        switch (sUriMatcher.match(uri)){
            case MOVIE_LIST: {
                retCursor = database.query(
                        DBContract.MovieEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            }
            case MOVIE_DETAIL: {
                retCursor = database.query(
                        DBContract.MovieEntry.TABLE_NAME, projection, DBContract.MovieEntry.COLUMN_ID + " = ?",
                        new String[]{uri.getLastPathSegment()}, null, null, sortOrder
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        throw new UnsupportedOperationException("Not needed");
    }
}
