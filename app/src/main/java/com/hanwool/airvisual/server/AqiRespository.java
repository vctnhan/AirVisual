package com.hanwool.airvisual.server;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hanwool.airvisual.model.PollutionApi;
import com.hanwool.airvisual.model.PollutionInfo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AqiRespository {
    private AqiService aqiService;
    private static AqiRespository aqiRespository;

    public AqiService getAqiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AqiService.BASE_URL)
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        aqiService = retrofit.create(AqiService.class);
        return aqiService;
    }

    public synchronized static AqiRespository getInstance() {
        if (aqiRespository == null) {
            if (aqiRespository == null) {
                aqiRespository = new AqiRespository();
            }
        }
        return aqiRespository;
    }

    public LiveData<PollutionInfo> getDataApi() {
        final MutableLiveData<PollutionInfo> data = new MutableLiveData<>();
        getAqiService().getDataApi().enqueue(new Callback<PollutionApi>() {
            @Override
            public void onResponse(Call<PollutionApi> call, Response<PollutionApi> response) {
        PollutionInfo pollutionInfo = new PollutionInfo();
        pollutionInfo.setmCity(response.body().getData().getCity().getName());
        pollutionInfo.setmIndex(String.valueOf(response.body().getData().getAqi()));
        pollutionInfo.setmClassification(processClassifition(response.body().getData().getAqi()));
        pollutionInfo.setMdateTime(convertDatetime(response.body().getData().getTime().getS()));
              data.setValue(pollutionInfo);
            }

            @Override
            public void onFailure(Call<PollutionApi> call, Throwable t) {

                data.setValue(null);
            }
        });
        return data;
    }
public String processClassifition(Integer mIndex){
        String mClassification;
    if (mIndex >= 0) {
        mClassification= "Good";
        return mClassification;
    } else if (mIndex >= 51) {
        mClassification ="Moderate";
        return mClassification;
    } else if (mIndex >= 101) {
        mClassification = "Unhealthy for sensitive";
        return mClassification;
    } else if (mIndex >= 151) {
        mClassification = "Unhealthy";
        return mClassification;
    } else if (mIndex >= 201) {
        mClassification = "Very unhealthy";
        return mClassification;
    } else if (mIndex >= 301) {
        mClassification = "Hazardous";
        return mClassification;
    }
    return "classification";
}
    public String convertDatetime(String inputDateStr) {

        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat outputFormat = new SimpleDateFormat("hh:mm:ss dd-MMM-yyyy");
        Date date = null;
        try {
            date = inputFormat.parse(inputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(date);
        return outputDateStr;
    }
}
