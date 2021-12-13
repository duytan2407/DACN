package com.example.dacn.API;

import com.example.dacn.modelclass.User;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Call;

public interface Methods {
    @POST("api/v1/user")
    Call<List<User>> getregister(@Body User user);
}
