package com.example.dacn.API;

import com.example.dacn.API.Model.User;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.Call;

public interface Methods {
    @GET("api/v1/user")
    Call<List<User>> getListUsers ();
}
