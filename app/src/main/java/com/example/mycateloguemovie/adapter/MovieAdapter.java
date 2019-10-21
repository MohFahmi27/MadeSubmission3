package com.example.mycateloguemovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mycateloguemovie.activity.DetailActivity;
import com.example.mycateloguemovie.R;
import com.example.mycateloguemovie.model.movie.Movies;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private List<Movies> Movies;

    public MovieAdapter(Context context, List<Movies> movies) {
        this.context = context;
        this.Movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_movie_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String judul = Movies.get(position).getOriginalTitle();
        if (judul.length() >= 20) {
            holder.tvJudulMovies.setText(judul.substring(0, 19) + "...");
        } else {
            holder.tvJudulMovies.setText(judul);
        }
        holder.tvDurationMovie.setText(Movies.get(position).getReleaseDate());
        holder.tvRatingMovie.setText(String.valueOf(Movies.get(position).getVoteAverage()));
        holder.tvGenreMovie.setText(Movies.get(position).getOriginalLanguage());
        String posterFilm = "https://image.tmdb.org/t/p/w500" + Movies.get(position).getPosterPath();
        Glide.with(context)
                .load(posterFilm)
                .apply(new RequestOptions().override(800, 850))
                .into(holder.imgPosterMovies);
    }

    @Override
    public int getItemCount() {
        return Movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.img_poster_movies)
        ImageView imgPosterMovies;
        @BindView(R.id.tv_judul_movies)
        TextView tvJudulMovies;
        @BindView(R.id.tv_duration_movie)
        TextView tvDurationMovie;
        @BindView(R.id.tv_genre_movie)
        TextView tvGenreMovie;
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.tv_rating_movie)
        TextView tvRatingMovie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Movies movies = Movies.get(position);

            Intent detail_movie = new Intent(itemView.getContext(), DetailActivity.class);
            detail_movie.putExtra(DetailActivity.EXTRA_DATA, movies);
            detail_movie.putExtra(DetailActivity.EXTRA_FROM, "fromMovie");
            itemView.getContext().startActivity(detail_movie);
        }
    }
}
