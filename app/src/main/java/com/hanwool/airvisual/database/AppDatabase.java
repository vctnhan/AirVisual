package com.hanwool.airvisual.database;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hanwool.airvisual.GlobalApplication;
import com.hanwool.airvisual.model.PollutionInfo;
import com.hanwool.airvisual.viewmodel.RecyclerviewModel;

import java.util.ArrayList;
import java.util.List;


@Database(entities = {PollutionKey.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract PollutionDao pollutionDao();


    public static AppDatabase getInMemoryDatabase(GlobalApplication globalApplication) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.inMemoryDatabaseBuilder(globalApplication.getAppContext(), AppDatabase.class)
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
    public void insertDb(String mCity, String mAqi, String mClassification, String mDatetime){
        if (getArrdb().size() == 0){
            AppDatabase appDatabase = AppDatabase.getInMemoryDatabase(new GlobalApplication());
            PollutionKey pollutionKey = new PollutionKey();
            pollutionKey.city = mCity;
            pollutionKey.air_index = mAqi;
            pollutionKey.classification = mClassification;
            pollutionKey.date_time = mDatetime;
            appDatabase.pollutionDao().insertPollution(pollutionKey);
        }
        else {

        }

    }
    public List<PollutionKey> getArrdb(){
        List<PollutionKey> arrDb = new ArrayList<>();
        arrDb = AppDatabase.getInMemoryDatabase(new GlobalApplication()).pollutionDao().findAllPollutionSync();
        return arrDb;
    }
    public List<RecyclerviewModel> getArrToLv(){
        List<RecyclerviewModel> arrToLv = new ArrayList<>();

        for (int i = 0; i < getArrdb().size(); i++) {
            PollutionInfo pollutionInfo = new PollutionInfo();
            pollutionInfo.setmCity(getArrdb().get(i).city);
            pollutionInfo.setmIndex(getArrdb().get(i).air_index);
            pollutionInfo.setMdateTime(getArrdb().get(i).date_time);
            pollutionInfo.setmClassification(getArrdb().get(i).classification);
            RecyclerviewModel recyclerviewModel = new RecyclerviewModel(pollutionInfo);
            arrToLv.add(recyclerviewModel);
        }
     return arrToLv;
    }
}