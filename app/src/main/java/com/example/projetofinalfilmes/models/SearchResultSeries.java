package com.example.projetofinalfilmes.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchResultSeries implements Serializable {

    @SerializedName("results")
    private ArrayList<Serie> seriesArrayList;

    public ArrayList<Serie> getSeriesArrayList() {
        return seriesArrayList;
    }

    public void setSeriesArrayList(ArrayList<Serie> seriesArrayList) {
        this.seriesArrayList = seriesArrayList;
    }
}
