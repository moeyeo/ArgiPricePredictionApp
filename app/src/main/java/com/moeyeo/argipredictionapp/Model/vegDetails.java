package com.moeyeo.argipredictionapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class vegDetails {
    @SerializedName("Name")
    @Expose
    String Name;
    @SerializedName("Harvest_Time")
    @Expose
    String Harvest_Time;

    public String getName() {
        return Name;
    }

    public String getHarvest_Time() {
        return Harvest_Time;
    }
}
