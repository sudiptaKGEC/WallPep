package com.sudiptahaldarkgec.wallpep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    AppCompatButton natureButton, busButton, carButton, trainButton, trendingButton;
    RecyclerView recyclerView;
    EditText searchText;
    ImageView searchButton;

    private ArrayList<ImgModel> modelClassList;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        natureButton = findViewById(R.id.natureButtonMainId);
        busButton = findViewById(R.id.busButtonMainId);
        carButton = findViewById(R.id.carButtonMainId);
        trainButton = findViewById(R.id.trainButtonMainId);
        trendingButton = findViewById(R.id.trendButtonMainId);
        recyclerView = findViewById(R.id.recycleViewMainId);
        searchText = findViewById(R.id.searchEditTextId);
        searchButton = findViewById(R.id.searchImgButton);

        modelClassList = new ArrayList<>();
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); /// number of picture in a row
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setHasFixedSize(true);
        adapter = new Adapter(getApplicationContext(), modelClassList);
        recyclerView.setAdapter(adapter);

        findPhotos();


        natureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "nature";
                getSearchImg(query);
            }
        });

        busButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "bus";
                getSearchImg(query);
            }
        });

        carButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "car";
                getSearchImg(query);
            }
        });

        trainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "train";
                getSearchImg(query);
            }
        });

        trendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPhotos();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = searchText.getText().toString().trim().toLowerCase(Locale.ROOT);
                if(query.isEmpty()){
                    Toast.makeText(MainActivity.this, "enter some thing", Toast.LENGTH_SHORT).show();
                }
                else {
                    getSearchImg(query);
                }
            }
        });



    }

    private void getSearchImg(String query) {
        ApiUtilities.getApiInterface().getSearchImage(query, 1, 88).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClassList.clear();
                if(response.isSuccessful()){
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(MainActivity.this, "not able to get", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }

    private void findPhotos() {
        ApiUtilities.getApiInterface().getImage(1, 80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                if(response.isSuccessful()){
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(MainActivity.this, "not able to get", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });

    }
}