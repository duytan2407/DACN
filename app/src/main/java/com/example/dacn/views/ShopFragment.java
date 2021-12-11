package com.example.dacn.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dacn.R;
import com.example.dacn.adapters.ShopListAdapter;
import com.example.dacn.databinding.FragmentShopBinding;
import com.example.dacn.models.Food;
import com.example.dacn.viewmodels.ShopViewModel;

import java.util.List;

public class ShopFragment extends Fragment implements ShopListAdapter.ShopInterface{
    private static final String TAG = "ShopFragment";
    FragmentShopBinding fragmentShopBinding;
    private ShopListAdapter shopListAdapter;
    public ShopViewModel shopViewModel;
    public NavController navController;

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

        shopListAdapter = new ShopListAdapter(this);
        fragmentShopBinding.shopRecyclerView.setAdapter(shopListAdapter);

        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getFoods().observe(getViewLifecycleOwner(), new Observer<List<Food>>() {
            @Override
            public void onChanged(List<Food> foods) {
                shopListAdapter.submitList(foods);
            }
        });
        navController = Navigation.findNavController(view);
    }

    @Override
    public void addItem(Food food) {

    }

    @Override
    public void onItemClick(Food food) {
//        Log.d(TAG, "onItemClick: " + food.toString());
        shopViewModel.setFood(food);
        navController.navigate(R.id.action_shopFragment_to_detailFragment);
    }
}