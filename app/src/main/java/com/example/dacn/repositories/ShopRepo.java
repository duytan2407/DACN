package com.example.dacn.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dacn.InterfaceRepository.Methods;
import com.example.dacn.models.Food;
import com.example.dacn.models.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopRepo {

    public MutableLiveData<List<Food>> mutableFoodList;

    public LiveData<List<Food>> getFoods(){
        if(mutableFoodList == null){
            mutableFoodList = new MutableLiveData<>();
            loadFood();
        }
        return mutableFoodList;
    }

    private void loadFood() {
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        Call<List<Food>> call = methods.loadFood();
        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
//                List<Product> data = response.body();
//                for(Product dt : data){
//                    Log.v("log:", data.getId());
                Log.d("log", "onResponse: Success");
//                }
                mutableFoodList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });
    }
}
