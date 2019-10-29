package com.hanwool.airvisual.viewmodel;

import android.app.Application;

import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.hanwool.airvisual.GlobalApplication;
import com.hanwool.airvisual.Respository;
import com.hanwool.airvisual.model.PollutionInfo;
import com.hanwool.airvisual.database.AppDatabase;
import com.hanwool.airvisual.database.PollutionKey;
import com.hanwool.airvisual.server.AqiRespository;

import java.util.List;

public class PollutionAqiViewModel extends AndroidViewModel {
    final LiveData<PollutionInfo> pollutionApiLiveData;
    public ObservableField<PollutionInfo> pollutionApiObservableField = new ObservableField<>();

    public PollutionAqiViewModel(Application application) {
        super(application);
        pollutionApiLiveData = Respository.getInstance().getDataApi();
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
Respository.getInstance().insertDatabase(nameCity(), airIndex(), classification(), dateTime());
    }

    public List<RecyclerviewModel> getArrToLv() {
        return Respository.getInstance().getArrToLv();
    }

}
