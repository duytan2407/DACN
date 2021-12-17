package com.example.shipperonly.Client.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiClient {
    //http://localhost:5000/api/v1/user?fbclid=IwAR1IfU8BrcxDWTawnOaPlotnlziqSpzCIn2tcq34FkhwJeenb68raIjVe4s
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();


    ApiClient apiclient = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiClient.class);
    @GET("api/v1/user")
    Call<List<User>> getListUsers ();
    @POST("api/v1/user")
    Call<List<User>> getregister(@Body User user);
}
