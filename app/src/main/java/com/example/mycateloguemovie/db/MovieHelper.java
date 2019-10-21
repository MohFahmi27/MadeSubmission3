package com.example.mycateloguemovie.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static com.example.mycateloguemovie.db.DatabaseContract.TABLE_FAVORITE_MOVIE;
import static com.example.mycateloguemovie.db.DatabaseContract.favoriteColumns.ID;
import static com.example.mycateloguemovie.db.DatabaseContract.favoriteColumns.TITLE;

public class MovieHelper {
    private static String DATABASE_TABLE_MOVIE = TABLE_FAVORITE_MOVIE;
    private Context context;
    private DatabaseHelper helper;
    private SQLiteDatabase database;

    public MovieHelper(Context context) {
        this.context = context;
    }

    public MovieHelper open() throws SQLException {
        helper = new DatabaseHelper(context);
        database = helper.getWritableDatabase();
        return this;
    }

    public Cursor queryByIdProvider(String movieId) {
        return database.query(DATABASE_TABLE_MOVIE,
                null,
                ID + " = ?",
                new String[]{movieId},
                null,
                null,
                null,
                null);
    }

    public Cursor queryProvider() {
        return database.query(DATABASE_TABLE_MOVIE,
                null,
                null,
                null,
                null,
                null,
                TITLE + " ASC");
    }

    public long insertProvider(ContentValues values) {
        return database.insert(DATABASE_TABLE_MOVIE, null, values);
    }

    public int deleteProvider(String id) {
        return database.delete(DATABASE_TABLE_MOVIE, ID + " = ?", new String[]{id});
    }
}
