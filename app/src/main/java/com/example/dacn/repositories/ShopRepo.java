package com.example.dacn.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dacn.models.Food;

import java.util.List;

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
    }
}
