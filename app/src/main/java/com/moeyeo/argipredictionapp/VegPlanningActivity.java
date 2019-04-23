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

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VegPlanningActivity extends AppCompatActivity {

    private static final String KEY_NAME = "NAME";

    public static void start(Context context, String vegName) {
        Intent starter = new Intent(context, VegPlanningActivity.class);
        starter.putExtra(KEY_NAME, vegName);
        context.startActivity(starter);
    }

    LineGraphSeries<DataPoint> series;
    ImageView pic;
    TextView name;
    Context mContext;
    String vegName;
    TextView avgPrice;
    TextView harvest;
    GraphView graph;
    List<Double> predictions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veg_planning);
        pic = (ImageView) findViewById(R.id.imageView2);
        mContext = this;
        name = (TextView) findViewById(R.id.vegName);
        vegName = getIntent().getStringExtra(KEY_NAME);
        predictions = new ArrayList<>();
        graph = (GraphView) findViewById(R.id.graph1);
        avgPrice = (TextView) findViewById(R.id.avgPrice);
        harvest = (TextView) findViewById(R.id.harvest2);

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
                predictions = response.body().getPredictions();
                avgPrice.setText(response.body().getAvgPrice()+" THB");
                harvest.setText("harvested in : "+response.body().getHarvestTime()+" months.");
                Glide.with(mContext).load(response.body().getImgUrl()).centerCrop().into(pic);
                createGraph();
            }

            @Override
            public void onFailure(Call<vegDetails> call, Throwable t) {

            }

        });


    }
    List<Double> prediction;
    private void createGraph(){
        NumberFormat formatter = new DecimalFormat("#0.00");
        System.out.println(formatter.format(4.0));
        prediction = new ArrayList<>();

        for(int i =0;i<12;i++){
            prediction.add(Double.parseDouble(formatter.format(predictions.get(i))));
            System.out.println(prediction.get(i));
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, this.predictions.get(0)),
                new DataPoint(2, this.predictions.get(1)),
                new DataPoint(3, this.predictions.get(2)),
                new DataPoint(4, this.predictions.get(3)),
                new DataPoint(5, this.predictions.get(4)),
                new DataPoint(6, this.predictions.get(5)),
                new DataPoint(7, this.predictions.get(6)),
                new DataPoint(8, this.predictions.get(7)),
                new DataPoint(9, this.predictions.get(8)),
                new DataPoint(10, this.predictions.get(9)),
                new DataPoint(11, this.predictions.get(10)),
                new DataPoint(12, this.predictions.get(11)),
        });
        graph.getViewport().setScalable(true);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(12);
        graph.addSeries(series);
    }
}

