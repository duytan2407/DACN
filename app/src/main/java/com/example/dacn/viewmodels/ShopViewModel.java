package com.example.dacn.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dacn.models.Food;
import com.example.dacn.repositories.ShopRepo;

import java.util.List;

public class ShopViewModel extends ViewModel {

    ShopRepo shopRepo = new ShopRepo();

    MutableLiveData<Food> mutableFood = new MutableLiveData<>();
    public LiveData<List<Food>> getFoods(){
        return shopRepo.getFoods();
    }
    public void setFood(Food food){
        mutableFood.setValue(food);
    }
    public LiveData<Food> getFood(){
        return mutableFood;
    }
}
