package com.hanwool.airvisual;

import androidx.lifecycle.LiveData;

import com.hanwool.airvisual.database.AppDatabase;
import com.hanwool.airvisual.database.PollutionKey;
import com.hanwool.airvisual.databinding.AqiBinding;
import com.hanwool.airvisual.model.PollutionInfo;
import com.hanwool.airvisual.server.AqiRespository;
import com.hanwool.airvisual.viewmodel.RecyclerviewModel;

import java.util.List;

public class Respository implements IRepositoryCallback {
    private  static Respository sRespository;
    private AppDatabase appDatabase = AppDatabase.getInMemoryDatabase(new GlobalApplication());


    public synchronized static Respository getInstance() {
        if (sRespository == null) {
            sRespository = new Respository();
        }
        return sRespository;
    }

    @Override
    public void insertDatabase(String mCity, String mAqi, String mClassification, String mDatetime) {
        appDatabase.insertDb(mCity, mAqi, mClassification, mDatetime);
    }

    @Override
    public List<PollutionKey> getDatabase() {
        return appDatabase.getArrdb();
    }

    @Override
    public List<RecyclerviewModel> getArrToLv() {
        return appDatabase.getArrToLv();
    }

    @Override
    public LiveData<PollutionInfo> getDataApi() {
        return AqiRespository.getInstance().getDataApi();
    }
}
