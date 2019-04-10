package com.moeyeo.argipredictionapp;

import android.gesture.Prediction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;

import com.moeyeo.argipredictionapp.Model.VegAdapter;
import com.moeyeo.argipredictionapp.Model.VegList;
import com.moeyeo.argipredictionapp.Model.vegDetails;
import com.moeyeo.argipredictionapp.Model.vegPriceGenerate;

import org.w3c.dom.Text;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PredictionActivity extends AppCompatActivity {

    SearchView sv;
    RecyclerView recyclerView;
    VegAdapter vegAdapter;
    List<vegDetails> listVeg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prediction_layout);

        recyclerView = (RecyclerView) findViewById(R.id.vegView);
        listVeg = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:7777/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        vegPriceGenerate veg = retrofit.create(vegPriceGenerate.class);
        Call call = veg.getVegList();
        call.enqueue(new Callback<VegList>() {
            @Override
            public void onResponse(Call<VegList> call, Response<VegList> response) {
                listVeg = response.body().result;
                vegAdapter.setFilms(response.body().result);
                System.out.print("Connect");
            }

            @Override
            public void onFailure(Call<VegList> call, Throwable t) {
                System.out.print("Can't connect");
            }
        });
        vegAdapter = new VegAdapter(this,listVeg, new VegAdapter.OnFilmClickListener() {
            @Override
            public void onFilmClick(vegDetails film) {
                VegDetailsActivity.start(PredictionActivity.this, film.getName());
                //Toast.makeText(PredictionActivity.this, film.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(vegAdapter);
        //recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sv = (SearchView) findViewById(R.id.searchView);
        sv.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(PredictionActivity.this, s, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        //System.out.print(sv.getQuery());

    }

}
