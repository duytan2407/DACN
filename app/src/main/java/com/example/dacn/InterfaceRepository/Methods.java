package com.example.dacn.InterfaceRepository;

import com.example.dacn.models.Food;
import com.example.dacn.models.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Methods {
//    @GET("MonAn")
    @GET("api/v1/food")
    Call<List<Food>> loadFood();
    @POST("api/v1/donhang")
    Call<Order> createOrder(@Body Order order);
//    @PUT("MonAn/{id}")
//    Call<Product> updateClass(@Path("id") String id, @Body Product product);
//    @DELETE("MonAn/{id}")
//    Call<Product> deleteClass(@Path("id") String id);
}
