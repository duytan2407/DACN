package com.example.dacn.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dacn.models.CartItem;
import com.example.dacn.models.Food;

import java.util.ArrayList;
import java.util.List;

public class CartRepo {

    private MutableLiveData<List<CartItem>> mutableCart = new MutableLiveData<>();

    public LiveData<List<CartItem>> getCart(){
        if(mutableCart.getValue() == null){
            initCart();
        }
        return mutableCart;
    }

    public void initCart() {
        mutableCart.setValue(new ArrayList<CartItem>());
//        caculateCartTotal();
    }

    public boolean addItemToCart(Food food){
        if(mutableCart.getValue() == null){
            initCart();
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
//        for(CartItem cartItem : cartItemList){
//            if(cartItem.getProduct().getId().equals(product.getId())){
//                if(cartItem.getQuantity() == 5){
//                    return false;
//                }
//                int index = cartItemList.indexOf(cartItem);
//                cartItem.setQuantity(cartItem.getQuantity() + 1);
//                cartItemList.set(index, cartItem);
//                mutableCart.setValue(cartItemList);
//                caculateCartTotal();
//                return true;
//            }
//        }
//
        CartItem cartItem = new CartItem(food, 1);
        cartItemList.add(cartItem);
        mutableCart.setValue(cartItemList);
//        caculateCartTotal();
        return true;
    }
}
