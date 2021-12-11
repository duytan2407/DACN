package com.example.dacn.InterfaceRepository;

import com.example.dacn.models.Food;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Methods {
//    @GET("MonAn")
    @GET("api/v1/food")
    Call<List<Food>> loadFood();
//    @POST("MonAn")
//    Call<Product> insertClass(@Body Product product);
//    @PUT("MonAn/{id}")
//    Call<Product> updateClass(@Path("id") String id, @Body Product product);
//    @DELETE("MonAn/{id}")
//    Call<Product> deleteClass(@Path("id") String id);
}
