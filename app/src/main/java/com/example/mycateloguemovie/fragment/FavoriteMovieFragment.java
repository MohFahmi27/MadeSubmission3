package com.example.mycateloguemovie.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycateloguemovie.R;
import com.example.mycateloguemovie.adapter.FavoriteMovieAdapter;
import com.example.mycateloguemovie.db.MovieHelper;
import com.example.mycateloguemovie.model.movie.Movies;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment {
    @BindView(R.id.rv_favorite_movie)
    RecyclerView rvFavoriteMovie;
    private FavoriteMovieAdapter favoriteMovieAdapter;
    private ArrayList<Movies> movies = new ArrayList<>();
    private MovieHelper movieHelper;

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFavoriteMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFavoriteMovie.setHasFixedSize(true);

        movieHelper = new MovieHelper(getContext());
        movieHelper.open();

        favoriteMovieAdapter = new FavoriteMovieAdapter(getContext());


    }
}
