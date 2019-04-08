package com.moeyeo.argipredictionapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
