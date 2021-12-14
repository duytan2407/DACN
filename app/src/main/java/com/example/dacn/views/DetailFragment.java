package com.example.dacn.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dacn.R;
import com.example.dacn.databinding.FragmentDetailBinding;
import com.example.dacn.viewmodels.ShopViewModel;

public class DetailFragment extends Fragment {

    FragmentDetailBinding fragmentDetailBinding;
    ShopViewModel shopViewModel;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false);
        return fragmentDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        fragmentDetailBinding.setShopViewModel(shopViewModel);
    }
}