package com.hanwool.airvisual.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.hanwool.airvisual.GlobalApplication;
import com.hanwool.airvisual.model.PollutionApi;
import com.hanwool.airvisual.model.PollutionInfo;
import com.hanwool.airvisual.respository.database.AppDatabase;
import com.hanwool.airvisual.respository.database.PollutionKey;
import com.hanwool.airvisual.respository.server.AqiRespository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PollutionAqiViewModel extends AndroidViewModel {
    final LiveData<PollutionInfo> pollutionApiLiveData;
    public ObservableField<PollutionInfo> pollutionApiObservableField = new ObservableField<>();
    public AppDatabase appDatabase = AppDatabase.getInMemoryDatabase(new GlobalApplication());

    public PollutionAqiViewModel(Application application) {
        super(application);
        pollutionApiLiveData = AqiRespository.getInstance().getDataApi();
        // nodata here
    }

    public LiveData<PollutionInfo> getObservableProject() {
        return pollutionApiLiveData;
    }

    //
    public void setPollutionApiViewModel(PollutionInfo pollutionInfo) {
        this.pollutionApiObservableField.set(pollutionInfo);
        insertDb();
    }

    public String nameCity() {

        return pollutionApiObservableField.get().getmCity();

    }

    public String airIndex() {
        return pollutionApiObservableField.get().getmIndex();
    }

    public String classification() {
        return pollutionApiObservableField.get().getmClassification();
    }

    public String dateTime() {
        return pollutionApiObservableField.get().getMdateTime();
    }

    public void insertDb() {
        appDatabase.insertDb(nameCity(), airIndex(), classification(), dateTime());
    }

    public List<RecyclerviewModel> getArrToLv() {
        return appDatabase.getArrToLv();
    }

}
