package com.example.mycateloguemovie.api;

import com.example.mycateloguemovie.model.movie.MoviesResponse;
import com.example.mycateloguemovie.model.tvshow.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClient {
    @GET("movie/now_playing")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);
    @GET("tv/airing_today")
    Call<TvShowResponse> getTvshowAiring(@Query("api_key") String apiKey);
}
