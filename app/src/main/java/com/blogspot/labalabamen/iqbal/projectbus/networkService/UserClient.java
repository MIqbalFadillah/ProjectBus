package com.blogspot.labalabamen.iqbal.projectbus.networkService;

import com.blogspot.labalabamen.iqbal.projectbus.model.Login;
import com.blogspot.labalabamen.iqbal.projectbus.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserClient {
    @POST("auth/login")
    Call<User> login(@Body Login login);

    @GET("api/buses/")
    Call<ResponseBody>getBuses(@Header("Authorization") String authToken);
}
