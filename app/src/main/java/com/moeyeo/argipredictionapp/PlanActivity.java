package com.moeyeo.argipredictionapp;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.moeyeo.argipredictionapp.Model.VegAdapter;
import com.moeyeo.argipredictionapp.Model.VegList;
import com.moeyeo.argipredictionapp.Model.vegDetails;
import com.moeyeo.argipredictionapp.Model.vegPriceGenerate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.Integer.parseInt;

public class PlanActivity extends AppCompatActivity {

    Button calButton;
    SimpleDateFormat sdf;
    SimpleDateFormat sdf2;
    RecyclerView recyclerView;
    VegAdapter vegAdapter;
    List<vegDetails> listVeg;
    String month;
    List<vegDetails> res;
    TextView notFound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_layout);
        calButton = (Button) findViewById(R.id.button2);
        recyclerView = (RecyclerView) findViewById(R.id.result);
        listVeg = new ArrayList<>();
        res = new ArrayList<>();
        notFound = (TextView) findViewById(R.id.notFound2);

        final Calendar myCalendar = Calendar.getInstance();

        final EditText edittext= (EditText) findViewById(R.id.startDate);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                myCalendar.set(Calendar.YEAR, i);
                myCalendar.set(Calendar.MONTH, i1);
                myCalendar.set(Calendar.DAY_OF_MONTH, i2);
                String myFormat = "dd/MM/yy"; //In which you need put here
                sdf = new SimpleDateFormat(myFormat, Locale.US);
                edittext.setText(sdf.format(myCalendar.getTime()));
                String myFormat2 = "M"; //In which you need put here
                sdf2 = new SimpleDateFormat(myFormat2, Locale.US);
                month = sdf2.format(myCalendar.getTime());

            }

        };
        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(PlanActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        calButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        try {
            System.out.println("\n" + month);
            showResult();
        } catch (Exception e){

        }

            }
        });


    }

    private void showResult(){

        listVeg = new ArrayList<>();
        res = new ArrayList<>();

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
                for(int i=0;i<listVeg.size();i++){
                    try{
                    checkIsFit(listVeg.get(i));}
                    catch (Exception e){

                    }
                }
                if(res.size()==0){
                    notFound.setText("Not found.");
                }
                showList();
                System.out.print("Connect");
            }

            @Override
            public void onFailure(Call<VegList> call, Throwable t) {
                System.out.print("Can't connect");
            }
        });

    }

    private void checkIsFit(final vegDetails vegDetail){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:7777/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            vegPriceGenerate veg = retrofit.create(vegPriceGenerate.class);
            Call call = veg.getVeg(vegDetail.getName());
            call.enqueue(new Callback<vegDetails>() {
                @Override
                public void onResponse(Call<vegDetails> call, Response<vegDetails> response) {
                    try {
                    int hMonth = parseInt(month);
                    int m = parseInt(response.body().getHarvestTime());
                    hMonth = hMonth + m;
                    if (hMonth > 12)
                        hMonth = hMonth % 12;
                    if (hMonth == response.body().getBestPrice()) {
                        System.out.println("\n" + vegDetail.getName());
                        res.add(vegDetail);
                        vegAdapter.setFilms(res);
                        notFound.setText("");
                    }
                    System.out.println("\n@@" + hMonth);
                    //showList();
                    } catch (Exception e){

                    }
                }

                @Override
                public void onFailure(Call<vegDetails> call, Throwable t) {

                }
            });
    }

    private void showList(){
        vegAdapter = new VegAdapter(this,res, new VegAdapter.OnFilmClickListener() {
            @Override
            public void onFilmClick(vegDetails film) {
                VegDetailsActivity.start(PlanActivity.this, film.getName());
                //Toast.makeText(PredictionActivity.this, film.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(vegAdapter);
        //recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
