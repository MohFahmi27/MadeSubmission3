package com.example.mycateloguemovie.viewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycateloguemovie.api.ApiClient;
import com.example.mycateloguemovie.BuildConfig;
import com.example.mycateloguemovie.model.tvshow.TvShow;
import com.example.mycateloguemovie.model.tvshow.TvShowResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvViewModel extends ViewModel {
    private static final String API_KEY = BuildConfig.TMDB_API_KEY;
    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private MutableLiveData<List<TvShow>> listTvShow = new MutableLiveData<>();

    public LiveData<List<TvShow>> getTvShowLiveData(Context context) {
        setTvshow(context);
        return listTvShow;
    }

    public void setTvshow(final Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final ApiClient apiTvshow = retrofit.create(ApiClient.class);
        Call<TvShowResponse> call = apiTvshow.getTvshowAiring(API_KEY);
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                listTvShow.setValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                Toast.makeText(context, "404 : Data Not Found", Toast.LENGTH_LONG).show();
            }
        });
    }


}
