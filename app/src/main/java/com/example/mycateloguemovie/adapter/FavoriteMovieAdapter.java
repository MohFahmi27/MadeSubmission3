package com.example.mycateloguemovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mycateloguemovie.R;
import com.example.mycateloguemovie.activity.DetailActivity;
import com.example.mycateloguemovie.model.movie.Movies;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteViewHolder> {

    private Context context;
    private Cursor cursor;
    private ArrayList<Movies> moviesList;

    public FavoriteMovieAdapter(Context context) {
        this.context = context;
    }

    public void setMoviesList(Cursor cursor) {
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.list_movie_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        final Movies movies = getItem(position);
        String judul = movies.getOriginalTitle();
        if (judul.length() >= 20) {
            holder.tvJudulMovies.setText(judul.substring(0, 19) + "...");
        } else {
            holder.tvJudulMovies.setText(judul);
        }
        holder.tvRatingMovie.setText(String.valueOf(movies.getVoteAverage()));
        holder.tvGenreMovie.setText(movies.getOriginalLanguage());
        holder.tvDurationMovie.setText(movies.getReleaseDate());
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500"+ movies.getPosterPath())
                .apply(new RequestOptions().override(800, 850))
                .into(holder.imgPosterMovies);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail_movie = new Intent(context, DetailActivity.class);
                detail_movie.putExtra(DetailActivity.EXTRA_DATA, movies);
                detail_movie.putExtra(DetailActivity.EXTRA_FROM, "fromMovie");
                context.startActivity(detail_movie);
            }
        });

    }

    private Movies getItem(int position) {
        if (!cursor.moveToPosition(position)) {
            throw new IllegalStateException("Invalid");
        }
        return new Movies(cursor);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_poster_movies)
        ImageView imgPosterMovies;
        @BindView(R.id.tv_judul_movies)
        TextView tvJudulMovies;
        @BindView(R.id.tv_duration_movie)
        TextView tvDurationMovie;
        @BindView(R.id.tv_genre_movie)
        TextView tvGenreMovie;
        @BindView(R.id.tv_rating_movie)
        TextView tvRatingMovie;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
