package com.example.projetofinalfilmes.ui.discover;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.projetofinalfilmes.R;
import com.example.projetofinalfilmes.models.Movie;
import com.example.projetofinalfilmes.models.SearchResult;
import com.example.projetofinalfilmes.network.GetDataService;
import com.example.projetofinalfilmes.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverFragment extends Fragment {

    private DiscoverViewModel homeViewModel;

    private static String API_KEY = "7c0d1ae5e4fa7e4a562859f06f4c7c3a";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_discover, container, false);

        GetDataService retrofitInterface = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<SearchResult> call = retrofitInterface.getTrending("all", API_KEY);

        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(@NonNull Call<SearchResult> call, @NonNull Response<SearchResult> response) {
                Log.d("Movie Name", response.body().getMovieArrayList().get(0).getName());
            }

            @Override
            public void onFailure(@NonNull Call<SearchResult> call, @NonNull Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

        return root;
    }
}
