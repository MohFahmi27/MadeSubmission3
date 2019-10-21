package com.example.mycateloguemovie.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mycateloguemovie.R;
import com.example.mycateloguemovie.model.movie.Movies;
import com.example.mycateloguemovie.model.tvshow.TvShow;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.img_detail_poster)
    ImageView imgDetailPoster;
    @BindView(R.id.tv_detail_judul)
    TextView tvDetailJudul;
    @BindView(R.id.tv_detail_genre)
    TextView tvDetailGenre;
    @BindView(R.id.tv_detail_rating)
    TextView tvDetailRating;
    @BindView(R.id.tv_detail_duration)
    TextView tvDetailDuration;
    @BindView(R.id.tv_detail_director)
    TextView tvDetailDirector;
    @BindView(R.id.tv_detail_synopsis)
    TextView tvDetailSynopsis;
    @BindView(R.id.detail_progress_bar)
    ProgressBar detailProgressBar;
    @BindView(R.id.detail_layout)
    ConstraintLayout detailLayout;
    public static final String EXTRA_DATA = "extra_data";
    public static final String EXTRA_FROM = "from_where";
    @BindView(R.id.toolbar_detail)
    Toolbar toolbarDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbarDetail);
        toolbarDetail.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbarDetail.setNavigationOnClickListener(view -> finish());

        showLoading(true);
        detailLayout.setVisibility(View.INVISIBLE);
        loadingCreated();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void loadingCreated() {
        //membuat loading dengan waktu tunggu 2 detik
        final Handler handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        detailLayout.setVisibility(View.VISIBLE);
                        String getFromWhere = getIntent().getStringExtra(EXTRA_FROM);

                        if (getFromWhere.equals("fromMovie")) {
                            showMovie();
                            showLoading(false);
                        }
                        if (getFromWhere.equals("fromTvshow")) {
                            showTvshow();
                            showLoading(false);
                        }
                    }
                });
            }
        }).start();
    }

    private void showTvshow() {
        TvShow tvShow = getIntent().getParcelableExtra(EXTRA_DATA);
        String imgPosterPath = "https://image.tmdb.org/t/p/w500" + tvShow.getPosterPath();
        Glide.with(this).load(imgPosterPath).apply(new RequestOptions().override(900, 1000)).into(imgDetailPoster);
        String name = tvShow.getName();
        String originalName = tvShow.getOriginalName();
        String rating = String.valueOf(tvShow.getVoteAverage());
        if (name.equals(originalName)) {
            tvDetailJudul.setText(name);
            tvDetailGenre.setVisibility(View.GONE);
        }
        if (!name.equals(originalName)) {
            tvDetailJudul.setText(name);
            tvDetailGenre.setText(originalName);
        }
        tvDetailDuration.setText(tvShow.getFirstAirDate());
        tvDetailDirector.setText(tvShow.getOriginalLanguage());
        tvDetailRating.setText(rating);
        tvDetailSynopsis.setText(tvShow.getOverview());
        toolbarDetail.setTitle(tvShow.getName());
    }

    private void showMovie() {
        Movies movies = getIntent().getParcelableExtra(EXTRA_DATA);
        String imgPosterPath = "https://image.tmdb.org/t/p/w500" + movies.getPosterPath();
        Glide.with(this).load(imgPosterPath).apply(new RequestOptions().override(900, 1000)).into(imgDetailPoster);
        String name = movies.getTitle();
        String rating = String.valueOf(movies.getVoteAverage());
        String originalName = movies.getOriginalTitle();
        if (name.equals(originalName)) {
            tvDetailJudul.setText(name);
            tvDetailGenre.setVisibility(View.GONE);
        }
        if (!name.equals(originalName)) {
            tvDetailJudul.setText(name);
            tvDetailGenre.setText(originalName);
        }
        tvDetailDirector.setText(movies.getOriginalLanguage());
        tvDetailSynopsis.setText(movies.getOverview());
        tvDetailRating.setText(rating);
        tvDetailDuration.setText(movies.getReleaseDate());
        toolbarDetail.setTitle(movies.getTitle());
    }

    private void showLoading(Boolean state) {
        if (state) {
            detailProgressBar.setVisibility(View.VISIBLE);
        } else {
            detailProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.favorite_action) {

        }
        return super.onOptionsItemSelected(item);
    }
}
