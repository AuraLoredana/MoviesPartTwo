package com.example.popescu.movies;

import android.content.ContentResolver;

import com.example.popescu.movies.db.DBContract;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;

/**
 * Created by POPESCU on 4/14/2018.
 */

class MoviesDB {
    static final String AUTHORITY_Uri = "content://" + DBContract.AUTHORITY;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public boolean isFavouriteMovie(ContentResolver contentResolver, int id){
        boolean retn = false;
        Cursor cursor = contentResolver.query(Uri.parse(AUTHORITY_Uri + "/" + id), null,null, null,null, null);
        if (cursor != null && cursor.moveToNext()){
            retn = true;
            cursor.close();
        }
        return retn;
    }
    public void addMovie(ContentResolver contentResolver, Movie movie){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.MovieEntry.COLUMN_ID, movie.id);
        contentValues.put(DBContract.MovieEntry.COLUMN_NAME, movie.display_name);
        contentValues.put(DBContract.MovieEntry.COLUMN_OVERVIEW, movie.overview);
        contentValues.put(DBContract.MovieEntry.COLUMN_POSTER, movie.poster_url);
        contentValues.put(DBContract.MovieEntry.COLUMN_RELEASE, movie.released_date);
        contentValues.put(DBContract.MovieEntry.COLUMN_RATING, movie.rating + "");
        contentResolver.insert(Uri.parse(AUTHORITY_Uri + "/movies"), contentValues);

    }

    public void removeMovie(ContentResolver contentResolver, int id){
        Uri uri = Uri.parse(AUTHORITY_Uri+"/"+id);
        contentResolver.delete(uri, null, new String[]{id + ""});
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ArrayList <Movie> getFavouriteMovies(ContentResolver contentResolver) {
        Uri uri = Uri.parse(AUTHORITY_Uri + "/movies");
        Cursor cursor = contentResolver.query(uri, null, null, null, null, null);
        ArrayList <Movie> movies = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.id = cursor.getInt(cursor.getColumnIndex(DBContract.MovieEntry.COLUMN_ID));
                movie.display_name = cursor.getString(cursor.getColumnIndex(DBContract.MovieEntry.COLUMN_NAME));
                movie.overview = cursor.getString(cursor.getColumnIndex(DBContract.MovieEntry.COLUMN_OVERVIEW));
                movie.rating = Float.parseFloat(cursor.getString(cursor.getColumnIndex(DBContract.MovieEntry.COLUMN_RATING)));
                movie.poster_url = cursor.getString(cursor.getColumnIndex(DBContract.MovieEntry.COLUMN_POSTER));
                movie.released_date = cursor.getString(cursor.getColumnIndex(DBContract.MovieEntry.COLUMN_RELEASE));
                movies.add(movie);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return movies;
    }
}
