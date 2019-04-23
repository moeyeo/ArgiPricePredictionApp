package com.moeyeo.argipredictionapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class vegDetails {
    @SerializedName("Name")
    @Expose
    String Name;

    @SerializedName("HarvestTime")
    @Expose
    String HarvestTime;

    @SerializedName("ImgUrl")
    @Expose
    String imgUrl;

    @SerializedName("BestPrice")
    @Expose
    int bestPrice;

    @SerializedName("Predictions")
    @Expose
    List<Double> predictions;

    @SerializedName("AvgPrice")
    @Expose
    Double avgPrice;

    public Double getAvgPrice() {
        return avgPrice;
    }

    public int getBestPrice() {
        return bestPrice;
    }

    public List<Double> getPredictions() {
        return predictions;
    }

    public String getName() {
        return Name;
    }

    public String getHarvestTime() {
        return HarvestTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
