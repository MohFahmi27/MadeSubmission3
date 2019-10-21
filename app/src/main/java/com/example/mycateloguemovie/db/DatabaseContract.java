package com.example.mycateloguemovie.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String TABLE_FAVORITE_MOVIE = "favorite_movie";
    public static final String TABLE_FAVORITE_TVSHOW = "favorite_tvshow";
    public static final String AUTHORITY = "com.example.mycataloguemovie";
    private static final String SCHEME = "content";

    public static final class favoriteColumns implements BaseColumns {
        public static String ID  = "id";
        public static String POSTER = "poster";
        public static String TITLE = "title";
        public static String RATING = "rating";
        public static String RELEASE_DATE = "release_date";
        public static String OVERVIEW = "overview";
        public static String CATEGORY = "category";
    }

    public static final Uri CONTENT_URI_TVSHOW = new Uri.Builder()
            .scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TABLE_FAVORITE_TVSHOW)
            .build();

    public static final Uri CONTENT_URI_MOVIE = new Uri.Builder()
            .scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TABLE_FAVORITE_MOVIE)
            .build();


    public static final String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

}
