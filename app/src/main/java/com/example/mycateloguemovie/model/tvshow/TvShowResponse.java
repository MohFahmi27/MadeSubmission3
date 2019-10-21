package com.example.mycateloguemovie.model.tvshow;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<TvShow> results;


    public List<TvShow> getResults() {
        return results;
    }
}
