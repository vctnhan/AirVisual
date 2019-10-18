package com.hanwool.airvisual.viewmodel;

import androidx.databinding.BaseObservable;

import com.hanwool.airvisual.model.PollutionApi;
import com.hanwool.airvisual.model.PollutionInfo;

public class RecyclerviewModel extends BaseObservable {
    private PollutionInfo pollutionInfo = new PollutionInfo();

    public RecyclerviewModel(PollutionInfo pollutionInfo) {
        this.pollutionInfo = pollutionInfo;
    }
    public String nameCityToLV() {
        return pollutionInfo.getmCity();
    }

    public String airIndexToLV() {
        return pollutionInfo.getmIndex();
    }

    public String classificationToLV() {
        return pollutionInfo.getmClassification();
    }

    public String dateTimeToLV() {
        return pollutionInfo.getMdateTime();
    }


}
