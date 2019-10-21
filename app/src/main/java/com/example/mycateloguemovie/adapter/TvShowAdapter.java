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
import com.example.mycateloguemovie.model.tvshow.TvShow;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {

    private Context context;
    private List<TvShow> tvShows;

    public TvShowAdapter(Context context, List<TvShow> tvShows) {
        this.context = context;
        this.tvShows = tvShows;
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_tvshow_layout, parent, false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {
        String judulTv = tvShows.get(position).getName();
        if (judulTv.length() >= 20) {
            holder.tvJudulTvshow.setText(judulTv.substring(0, 19) + "...");
        } else {
            holder.tvJudulTvshow.setText(judulTv);
        }
        holder.tvEpisodeTvshow.setText(tvShows.get(position).getFirstAirDate());
        holder.tvRatingTvshow.setText(String.valueOf(tvShows.get(position).getVoteAverage()));
        holder.tvGenreTvshow.setText(tvShows.get(position).getOriginalLanguage());
        String posterFilm = "https://image.tmdb.org/t/p/w500" + tvShows.get(position).getPosterPath();
        Glide.with(context)
                .load(posterFilm)
                .apply(new RequestOptions().override(800, 850))
                .into(holder.imgPosterTvshow);
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.img_poster_tvshow)
        ImageView imgPosterTvshow;
        @BindView(R.id.tv_judul_tvshow)
        TextView tvJudulTvshow;
        @BindView(R.id.tv_episode_tvshow)
        TextView tvEpisodeTvshow;
        @BindView(R.id.tv_genre_tvshow)
        TextView tvGenreTvshow;
        @BindView(R.id.imageViewtvshow)
        ImageView imageViewtvshow;
        @BindView(R.id.tv_rating_tvshow)
        TextView tvRatingTvshow;

        TvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            TvShow tvShow = tvShows.get(position);

            Intent detailTvshow = new Intent(itemView.getContext(), DetailActivity.class);
            detailTvshow.putExtra(DetailActivity.EXTRA_DATA, tvShow);
            detailTvshow.putExtra(DetailActivity.EXTRA_FROM, "fromTvshow");
            itemView.getContext().startActivity(detailTvshow);
        }
    }
}
