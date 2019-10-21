package com.example.mycateloguemovie.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycateloguemovie.viewModel.MovieViewModel;
import com.example.mycateloguemovie.R;
import com.example.mycateloguemovie.adapter.MovieAdapter;
import com.example.mycateloguemovie.model.movie.Movies;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    @BindView(R.id.rv_movie)
    RecyclerView rvMovie;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private MovieAdapter movieAdapter;
    private MovieViewModel viewModel;

    private final Observer<List<Movies>> getMovies = new Observer<List<Movies>>() {
        @Override
        public void onChanged(@Nullable List<Movies> movies) {
            if (movies != null) {
                movieAdapter = new MovieAdapter(getContext(), movies);
                rvMovie.setAdapter(movieAdapter);
                showLoading(false);
            }
        }
    };

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        ButterKnife.bind(this, view);
        showLoading(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        viewModel.getMovies(this.getContext()).observe(this, getMovies);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
