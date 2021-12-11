package com.example.dacn.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.dacn.models.Food;
import com.example.dacn.repositories.ShopRepo;

import java.util.List;

public class ShopViewModel extends ViewModel {

    ShopRepo shopRepo = new ShopRepo();

    public LiveData<List<Food>> getFoods(){
        return shopRepo.getFoods();
    }

}
