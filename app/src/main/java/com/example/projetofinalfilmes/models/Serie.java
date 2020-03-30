package com.example.projetofinalfilmes.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Serie implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("first_air_date")
    private String airDate;

    @SerializedName("backdrop_path")
    private String backDropUrl;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("poster_path")
    private String posterUrl;

    @SerializedName("vote_average")
    private String rating;

    @SerializedName("overview")
    private String overview;

    @SerializedName("production_companies")
    private ArrayList<Company> companyArrayList;

    @SerializedName("genre_ids")
    private ArrayList<Integer> genreId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public String getBackDropUrl() {
        return backDropUrl;
    }

    public void setBackDropUrl(String backDropUrl) {
        this.backDropUrl = backDropUrl;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public ArrayList<Company> getCompanyArrayList() {
        return companyArrayList;
    }

    public void setCompanyArrayList(ArrayList<Company> companyArrayList) {
        this.companyArrayList = companyArrayList;
    }

    public ArrayList<Integer> getGenreId() {
        return genreId;
    }

    public void setGenreId(ArrayList<Integer> genreId) {
        this.genreId = genreId;
    }
}
