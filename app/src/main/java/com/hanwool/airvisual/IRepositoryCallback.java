package com.hanwool.airvisual;

import androidx.lifecycle.LiveData;

import com.hanwool.airvisual.database.PollutionKey;
import com.hanwool.airvisual.model.PollutionInfo;
import com.hanwool.airvisual.viewmodel.RecyclerviewModel;

import java.util.List;

public interface IRepositoryCallback {
    void insertDatabase(String mCity, String mAqi, String mClassification, String mDatetime);
    List<PollutionKey> getDatabase();
    List<RecyclerviewModel> getArrToLv();
    LiveData<PollutionInfo> getDataApi();
}
