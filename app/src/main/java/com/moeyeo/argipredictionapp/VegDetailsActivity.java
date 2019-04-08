package com.moeyeo.argipredictionapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.moeyeo.argipredictionapp.Model.vegDetails;
import com.moeyeo.argipredictionapp.Model.vegPriceGenerate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VegDetailsActivity extends AppCompatActivity {

    private static final String KEY_NAME = "NAME";

    public static void start(Context context, String vegName) {
        Intent starter = new Intent(context, VegDetailsActivity.class);
        starter.putExtra(KEY_NAME, vegName);
        context.startActivity(starter);
    }

    LineGraphSeries<DataPoint> series;
    ImageView pic;
    TextView name;
    Context mContext;
    String vegName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veg_details);
        pic = (ImageView) findViewById(R.id.imageView2);
        mContext = this;
        name = (TextView) findViewById(R.id.vegName);
        vegName = getIntent().getStringExtra(KEY_NAME);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:7777/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        vegPriceGenerate veg = retrofit.create(vegPriceGenerate.class);
        Call call = veg.getVeg(vegName);

        call.enqueue(new Callback<vegDetails>() {
            @Override
            public void onResponse(Call<vegDetails> call, Response<vegDetails> response) {
                name.setText(response.body().getName());
                Glide.with(mContext).load(response.body().getImgUrl()).centerCrop().into(pic);
            }

            @Override
            public void onFailure(Call<vegDetails> call, Throwable t) {

            }

        });

        double y,x;
        x = 0;
        y = 12;

        GraphView graph = (GraphView) findViewById(R.id.graph1);
        series = new LineGraphSeries<DataPoint>();
        for(int i =0; i<12; i++) {
            x = i;
            y = y-1;
            series.appendData(new DataPoint(x, y), true, 100);
        }
        graph.addSeries(series);;
    }
}
