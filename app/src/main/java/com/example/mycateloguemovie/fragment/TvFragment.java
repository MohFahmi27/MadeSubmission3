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

import com.example.mycateloguemovie.viewModel.TvViewModel;
import com.example.mycateloguemovie.R;
import com.example.mycateloguemovie.adapter.TvShowAdapter;
import com.example.mycateloguemovie.model.tvshow.TvShow;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment {
    @BindView(R.id.tv_progressbar)
    ProgressBar tvProgressbar;
    @BindView(R.id.rv_tvshow)
    RecyclerView rvTvshow;
    private TvViewModel viewModel;
    private TvShowAdapter tvShowAdapter;

    private final Observer<List<TvShow>> getTvshow = new Observer<List<TvShow>>() {
        @Override
        public void onChanged(@Nullable List<TvShow> tvShows) {
            if (tvShows != null) {
                tvShowAdapter = new TvShowAdapter(getContext(), tvShows);
                rvTvshow.setAdapter(tvShowAdapter);
                showLoading(false);
            }
        }
    };

    public TvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv, container, false);
        ButterKnife.bind(this, view);
        showLoading(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(TvViewModel.class);
        viewModel.getTvShowLiveData(this.getContext()).observe(this, getTvshow);
    }

    private void showLoading(Boolean state) {
        if (state) {
            tvProgressbar.setVisibility(View.VISIBLE);
        } else {
            tvProgressbar.setVisibility(View.GONE);
        }
    }
}
