package com.example.dacn.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dacn.models.CartItem;
import com.example.dacn.models.Food;

import java.util.ArrayList;
import java.util.List;

public class CartRepo {

    private MutableLiveData<List<CartItem>> mutableCart = new MutableLiveData<>();
    private MutableLiveData<Double> mutableTotalPrice = new MutableLiveData<>();

    public LiveData<List<CartItem>> getCart(){
        if(mutableCart.getValue() == null){
            initCart();
        }
        return mutableCart;
    }

    public void initCart() {
        mutableCart.setValue(new ArrayList<CartItem>());
        caculateCartTotal();
    }

    public boolean addItemToCart(Food food){
        if(mutableCart.getValue() == null){
            initCart();
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        for(CartItem cartItem : cartItemList){
            if(cartItem.getFood().getId().equals(food.getId())){
                if(cartItem.getQuantity() == 5){
                    return false;
                }
                int index = cartItemList.indexOf(cartItem);
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItemList.set(index, cartItem);
                mutableCart.setValue(cartItemList);
                caculateCartTotal();
                return true;
            }
        }

        CartItem cartItem = new CartItem(food, 1);
        cartItemList.add(cartItem);
        mutableCart.setValue(cartItemList);
        caculateCartTotal();
        return true;
    }

    public void removeItemFromCart(CartItem cartItem){
        if(mutableCart.getValue() == null){
            return;
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        cartItemList.remove(cartItem);
        mutableCart.setValue(cartItemList);
        caculateCartTotal();
    }

    public void changeQuantity(CartItem cartItem, int quantity){
        if(mutableCart.getValue() == null) return;
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        CartItem updateItem = new CartItem(cartItem.getFood(), quantity);
        cartItemList.set(cartItemList.indexOf(cartItem), updateItem);
        mutableCart.setValue(cartItemList);
        caculateCartTotal();
    }

    private void caculateCartTotal(){
        if(mutableCart.getValue() == null) return;
        Double total = 0.0;
        List<CartItem> cartItemList = mutableCart.getValue();
        for(CartItem cartItem: cartItemList){
            total += cartItem.getFood().getGia() * cartItem.getQuantity();
        }
        mutableTotalPrice.setValue(total);
    }

    public LiveData<Double> getTotalPrice(){
        if(mutableTotalPrice.getValue() == null){
            mutableTotalPrice.setValue(0.0);
        }
        return mutableTotalPrice;
    }
}
