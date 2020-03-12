package com.example.projetofinalfilmes.models;

import com.example.projetofinalfilmes.data.DatabaseHandler;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String name;

    @SerializedName("poster_path")
    private String posterUrl;

    @SerializedName("backdrop_path")
    private String backDropUrl;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseData;

    @SerializedName("genre_ids")
    private ArrayList<Integer> genreId;

    @SerializedName("runtime")
    private int runtime;

    @SerializedName("vote_average")
    private Double rating;

    @SerializedName("production_companies")
    private ArrayList<Company> companyArrayList;

    private ArrayList<Genre> arrayListGenres;

    public ArrayList<Company> getCompanyArrayList() {
        return companyArrayList;
    }

    public void setCompanyArrayList(ArrayList<Company> companyArrayList) {
        this.companyArrayList = companyArrayList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getBackDropUrl() {
        return backDropUrl;
    }

    public void setBackDropUrl(String backDropUrl) {
        this.backDropUrl = backDropUrl;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseData() {
        return releaseData;
    }

    public void setReleaseData(String releaseData) {
        this.releaseData = releaseData;
    }

    public ArrayList<Integer> getGenreId() {
        return genreId;
    }

    public void setGenreId(ArrayList<Integer> genreId) {
        this.genreId = genreId;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public ArrayList<Genre> getArrayListGenres() {
        return arrayListGenres;
    }

    public void setArrayListGenres(ArrayList<Genre> arrayListGenres) {
        this.arrayListGenres = arrayListGenres;
    }
}
