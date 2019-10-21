package com.example.mycateloguemovie.model.movie;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Movies> results;

    public List<Movies> getResults() {
        return results;
    }
}
