package com.example.projetofinalfilmes.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SerieResult {

    @SerializedName("results")
    private ArrayList<Serie> serieArrayList;

    public ArrayList<Serie> getSerieArrayList() {
        return serieArrayList;
    }

    public void setSerieArrayList(ArrayList<Serie> serieArrayList) {
        this.serieArrayList = serieArrayList;
    }

}
