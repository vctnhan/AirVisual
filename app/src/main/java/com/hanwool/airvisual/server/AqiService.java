package com.hanwool.airvisual.server;

import com.hanwool.airvisual.model.PollutionApi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AqiService {
String BASE_URL ="https://api.waqi.info";
    @GET("/feed/hanoi/?token=d2e7e890a2014c900396dc1f505b4eba3d890bd1")
    Call<PollutionApi> getDataApi();
}
