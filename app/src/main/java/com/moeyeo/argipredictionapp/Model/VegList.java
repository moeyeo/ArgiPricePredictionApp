package com.moeyeo.argipredictionapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VegList {

    @SerializedName("result")
    @Expose List<vegDetails> result;

    public List<vegDetails> getResult() {
        return result;
    }
}
