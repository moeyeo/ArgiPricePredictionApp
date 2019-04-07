package com.moeyeo.argipredictionapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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

    LineGraphSeries<DataPoint> series;
    ImageView pic;
    TextView name;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veg_details);
        pic = (ImageView) findViewById(R.id.imageView2);
        mContext = this;
        name = (TextView) findViewById(R.id.vegName);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:7777/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        vegPriceGenerate veg = retrofit.create(vegPriceGenerate.class);
        Call call = veg.getVeg("Banana");

        call.enqueue(new Callback<vegDetails>() {
            @Override
            public void onResponse(Call<vegDetails> call, Response<vegDetails> response) {
                name.setText(response.body().getName());

            }

            @Override
            public void onFailure(Call<vegDetails> call, Throwable t) {

            }

        });

        Glide.with(mContext).load("https://3.imimg.com/data3/CA/TX/MY-10737453/cellery-leaf-250x250.jpg").centerCrop().into(pic);

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
