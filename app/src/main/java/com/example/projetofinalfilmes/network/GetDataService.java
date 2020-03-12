package com.example.projetofinalfilmes.network;

import com.example.projetofinalfilmes.models.Movie;
import com.example.projetofinalfilmes.models.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("/trending/{type}/week")
    Call<SearchResult> getTrending(@Path(value = "type", encoded = true) String type, @Query("api_key") String apiKey);


}
