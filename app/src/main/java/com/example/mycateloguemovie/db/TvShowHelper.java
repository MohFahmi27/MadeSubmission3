package com.example.mycateloguemovie.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static com.example.mycateloguemovie.db.DatabaseContract.TABLE_FAVORITE_TVSHOW;
import static com.example.mycateloguemovie.db.DatabaseContract.favoriteColumns.ID;
import static com.example.mycateloguemovie.db.DatabaseContract.favoriteColumns.TITLE;

public class TvShowHelper {
    private static String DATABASE_TABLE_TVSHOW = TABLE_FAVORITE_TVSHOW;
    private Context context;
    private FavoriteMovieHelper helper;
    private SQLiteDatabase database;

    public TvShowHelper(Context context) {
        this.context = context;
    }

    public TvShowHelper open() throws SQLException {
        helper = new FavoriteMovieHelper(context);
        database = helper.getWritableDatabase();
        return this;
    }

    public Cursor queryByIdProvider(String tvshowId) {
        return database.query(DATABASE_TABLE_TVSHOW,
                null,
                ID + " = ?",
                new String[]{tvshowId},
                null,
                null,
                null,
                null);
    }

    public Cursor queryProvider() {
        return database.query(DATABASE_TABLE_TVSHOW,
                null,
                null,
                null,
                null,
                null,
                TITLE + " ASC");
    }

    public long insertProvider(ContentValues values) {
        return database.insert(DATABASE_TABLE_TVSHOW, null, values);
    }

    public int deleteProvider(String id) {
        return database.delete(DATABASE_TABLE_TVSHOW, ID + " = ?", new String[]{id});
    }

}
