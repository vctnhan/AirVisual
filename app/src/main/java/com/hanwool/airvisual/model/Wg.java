
package com.hanwool.airvisual.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wg {

    @SerializedName("v")
    @Expose
    private Double v;

    public Double getV() {
        return v;
    }

    public void setV(Double v) {
        this.v = v;
    }

}
