package com.example.mycateloguemovie.viewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycateloguemovie.api.ApiClient;
import com.example.mycateloguemovie.model.movie.MoviesResponse;
import com.example.mycateloguemovie.BuildConfig;
import com.example.mycateloguemovie.model.movie.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieViewModel extends ViewModel {
    private static final String API_KEY = BuildConfig.TMDB_API_KEY;
    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private MutableLiveData<List<Movies>> listMovies = new MutableLiveData<>();

    public LiveData<List<Movies>> getMovies(Context context) {
        setMovies(context);
        return listMovies;
    }

    public void setMovies(final Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ApiClient apiInterface = retrofit.create(ApiClient.class);
        Call<MoviesResponse> call = apiInterface.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                listMovies.setValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Toast.makeText(context, "404 : Data Not Found", Toast.LENGTH_LONG).show();
            }
        });
    }
}
