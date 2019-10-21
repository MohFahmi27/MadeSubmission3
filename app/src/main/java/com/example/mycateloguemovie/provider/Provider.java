package com.example.mycateloguemovie.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.example.mycateloguemovie.db.DatabaseContract;
import com.example.mycateloguemovie.db.MovieHelper;
import com.example.mycateloguemovie.db.TvShowHelper;

import java.util.Objects;

import static com.example.mycateloguemovie.db.DatabaseContract.AUTHORITY;
import static com.example.mycateloguemovie.db.DatabaseContract.CONTENT_URI_MOVIE;
import static com.example.mycateloguemovie.db.DatabaseContract.CONTENT_URI_TVSHOW;
import static com.example.mycateloguemovie.db.DatabaseContract.TABLE_FAVORITE_MOVIE;
import static com.example.mycateloguemovie.db.DatabaseContract.TABLE_FAVORITE_TVSHOW;

public class Provider extends ContentProvider {
    private static final int MOVIE = 1;
    private static final int MOVIE_ID = 2;
    private static final int TVSHOW = 3;
    private static final int TVSHOW_ID = 4;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private MovieHelper movieHelper;
    private TvShowHelper tvShowHelper;

    static {
        uriMatcher.addURI(AUTHORITY, DatabaseContract.TABLE_FAVORITE_MOVIE, MOVIE);
        uriMatcher.addURI(AUTHORITY, DatabaseContract.TABLE_FAVORITE_MOVIE + "/#", MOVIE_ID);
    }

    static {
        uriMatcher.addURI(AUTHORITY, DatabaseContract.TABLE_FAVORITE_TVSHOW, TVSHOW);
        uriMatcher.addURI(AUTHORITY, TABLE_FAVORITE_TVSHOW + "/#", TVSHOW_ID);
    }

    @Override
    public boolean onCreate() {
        movieHelper = new MovieHelper(getContext());
        tvShowHelper = new TvShowHelper(getContext());
        movieHelper.open();
        tvShowHelper.open();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case MOVIE:
                cursor = movieHelper.queryProvider();
                break;
            case MOVIE_ID:
                cursor = movieHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            case TVSHOW:
                cursor = tvShowHelper.queryProvider();
                break;
            case TVSHOW_ID:
                cursor = tvShowHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        long added;
        Uri content = null;
        switch (uriMatcher.match(uri)) {
            case MOVIE:
                added = movieHelper.insertProvider(contentValues);
                content = CONTENT_URI_MOVIE;
                break;
            case TVSHOW:
                added = tvShowHelper.insertProvider(contentValues);
                content = CONTENT_URI_TVSHOW;
                break;
            default:
                added = 0;
                break;
        }
        if (added > 0 ) {
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        }
        return Uri.parse(content + "/" + added);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        int deleted;
        switch (uriMatcher.match(uri)) {
            case MOVIE_ID :
                deleted = movieHelper.deleteProvider(uri.getLastPathSegment());
                break;
            case TVSHOW_ID :
                deleted = tvShowHelper.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
        }
        if (deleted > 0) {
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        }
        return deleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
