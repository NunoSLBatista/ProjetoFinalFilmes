package com.example.projetofinalfilmes.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class GenreResult implements Serializable {

    @SerializedName("genres")
    private ArrayList<Genre> genreArrayList;

    public ArrayList<Genre> getGenreArrayList() {
        return genreArrayList;
    }

    public void setGenreArrayList(ArrayList<Genre> genreArrayList) {
        this.genreArrayList = genreArrayList;
    }
}
