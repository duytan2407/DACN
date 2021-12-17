package com.example.shipperonly.InterfaceRepository;


import com.example.shipperonly.models.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Methods {
    @GET("api/v1/donhang")
    Call<List<Order>> loadOrder();

//    @GET("MonAn")
//    Call<List<Food>> loadFood();
//    @POST("api/v1/donhang")
//    Call<Order> createOrder(@Body Order order);
//    @PUT("MonAn/{id}")
//    Call<Product> updateClass(@Path("id") String id, @Body Product product);
//    @DELETE("MonAn/{id}")
//    Call<Product> deleteClass(@Path("id") String id);
}
