package com.example.dacn.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dacn.R;
import com.example.dacn.adapters.ShopListAdapter;
import com.example.dacn.databinding.FragmentShopBinding;
import com.example.dacn.models.Food;

public class ShopFragment extends Fragment implements ShopListAdapter.ShopInterface{

    FragmentShopBinding fragmentShopBinding;
    private ShopListAdapter shopListAdapter;

    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentShopBinding = FragmentShopBinding.inflate(inflater, container, false);
        return fragmentShopBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopListAdapter = new ShopListAdapter();
        fragmentShopBinding.shopRecyclerView.setAdapter(shopListAdapter);

    }

    @Override
    public void addItem(Food food) {

    }

    @Override
    public void onItemClick(Food food) {

    }
}