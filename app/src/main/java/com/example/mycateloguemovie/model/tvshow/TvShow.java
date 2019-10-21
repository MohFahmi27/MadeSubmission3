package com.example.mycateloguemovie.model.tvshow;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TvShow implements Parcelable {
    @SerializedName("original_name")
    private String originalName;
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("vote_count")
    private Integer voteCount;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("genre_ids")
    private List<Integer> genreIds = null;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("overview")
    private String overview;
    @SerializedName("origin_country")
    private List<String> originCountry = null;

    public String getOriginalName() {
        return originalName;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPopularity() {
        return popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.originalName);
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeValue(this.popularity);
        dest.writeValue(this.voteCount);
        dest.writeValue(this.voteAverage);
        dest.writeString(this.firstAirDate);
        dest.writeString(this.posterPath);
        dest.writeList(this.genreIds);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.backdropPath);
        dest.writeString(this.overview);
        dest.writeStringList(this.originCountry);
    }

    public TvShow() {
    }

    protected TvShow(Parcel in) {
        this.originalName = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.firstAirDate = in.readString();
        this.posterPath = in.readString();
        this.genreIds = new ArrayList<Integer>();
        in.readList(this.genreIds, Integer.class.getClassLoader());
        this.originalLanguage = in.readString();
        this.backdropPath = in.readString();
        this.overview = in.readString();
        this.originCountry = in.createStringArrayList();
    }

    public static final Parcelable.Creator<TvShow> CREATOR = new Parcelable.Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel source) {
            return new TvShow(source);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };
}
