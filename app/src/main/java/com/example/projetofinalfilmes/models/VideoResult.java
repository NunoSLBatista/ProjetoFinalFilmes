package com.example.projetofinalfilmes.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class VideoResult implements Serializable {

    @SerializedName("results")
    private ArrayList<Video> videoArrayList;

    @SerializedName("id")
    private int id;

    public ArrayList<Video> getVideoArrayList() {
        return videoArrayList;
    }

    public void setVideoArrayList(ArrayList<Video> videoArrayList) {
        this.videoArrayList = videoArrayList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
