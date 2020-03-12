package com.example.projetofinalfilmes.network;

import com.example.projetofinalfilmes.models.GenreResult;
import com.example.projetofinalfilmes.models.Movie;
import com.example.projetofinalfilmes.models.SearchResult;
import com.example.projetofinalfilmes.models.VideoResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("/3/trending/{type}/week")
    Call<SearchResult> getTrending(@Path(value = "type", encoded = true) String type, @Query("api_key") String apiKey);

    @GET("/3/genre/movie/list")
    Call<GenreResult> getGenres(@Query("language") String language, @Query("api_key") String apiKey);

    @GET("3/discover/movie")
    Call<SearchResult> getRelated(@Query("language") String language, @Query("api_key") String apiKey,  @Query("include_adult") String includeAdult, @Query("include_video") String includeVideo,
                                  @Query("with_genres") String genres, @Query("sort_by") String sortBy, @Query("page") String page);

    @GET("/3/movie/{id}/videos")
    Call<VideoResult> getVideos(@Path(value = "id", encoded = true) String id, @Query("api_key") String apiKey, @Query("language") String language);

    @GET("/3/movie/{id}")
    Call<Movie> getMovie(@Path(value = "id", encoded = true) String id, @Query("api_key") String apiKey, @Query("language") String language);

    //?api_key=7c0d1ae5e4fa7e4a562859f06f4c7c3a&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_genres=28%2C%2012

}
