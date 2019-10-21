package com.example.mycateloguemovie.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db_favorite";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
    + "(%s INTEGER PRIMARY KEY AUTOINCREMENT, "+
    " %s TEXT NOT NULL, " +
    " %s TEXT NOT NULL, " +
    " %s TEXT NOT NULL, " +
    " %s TEXT NOT NULL, " +
    " %s TEXT NOT NULL, " +
    " %s TEXT NOT NULL)",
    DatabaseContract.TABLE_FAVORITE_MOVIE,
            DatabaseContract.favoriteColumns._ID,
            DatabaseContract.favoriteColumns.ID,
            DatabaseContract.favoriteColumns.TITLE,
            DatabaseContract.favoriteColumns.POSTER,
            DatabaseContract.favoriteColumns.OVERVIEW,
            DatabaseContract.favoriteColumns.RELEASE_DATE,
            DatabaseContract.favoriteColumns.RATING,
            DatabaseContract.favoriteColumns.CATEGORY);

    private static final String SQL_CREATE_TABLE_TVSHOW = String.format("CREATE TABLE %s"
                    + "(%s INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    " %s TEXT NOT NULL, " +
                    " %s TEXT NOT NULL, " +
                    " %s TEXT NOT NULL, " +
                    " %s TEXT NOT NULL, " +
                    " %s TEXT NOT NULL, " +
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_FAVORITE_MOVIE,
            DatabaseContract.favoriteColumns._ID,
            DatabaseContract.favoriteColumns.ID,
            DatabaseContract.favoriteColumns.TITLE,
            DatabaseContract.favoriteColumns.POSTER,
            DatabaseContract.favoriteColumns.OVERVIEW,
            DatabaseContract.favoriteColumns.RELEASE_DATE,
            DatabaseContract.favoriteColumns.RATING,
            DatabaseContract.favoriteColumns.CATEGORY);

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_MOVIE);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TVSHOW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FAVORITE_MOVIE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FAVORITE_TVSHOW);
        onCreate(sqLiteDatabase);
    }
}
