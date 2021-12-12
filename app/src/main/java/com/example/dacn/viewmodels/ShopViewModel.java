package com.example.dacn.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dacn.models.CartItem;
import com.example.dacn.models.Food;
import com.example.dacn.repositories.CartRepo;
import com.example.dacn.repositories.ShopRepo;

import java.util.List;

public class ShopViewModel extends ViewModel {

    ShopRepo shopRepo = new ShopRepo();
    CartRepo cartRepo = new CartRepo();

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

    public LiveData<List<CartItem>> getCart(){
        return cartRepo.getCart();
    }

    public boolean addItemToCart(Food food){
        return cartRepo.addItemToCart(food);
    }
}
