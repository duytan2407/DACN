package com.example.dacn.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.databinding.CartRowBinding;
import com.example.dacn.models.CartItem;

public class CartListAdapter extends ListAdapter<CartItem, CartListAdapter.CartVH> {

    public CartListAdapter() {
        super(CartItem.itemCallback);
    }

    @NonNull
    @Override
    public CartVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CartRowBinding cartRowBinding = CartRowBinding.inflate(layoutInflater, parent, false);
        return new CartVH(cartRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartVH holder, int position) {
        holder.cartRowBinding.setCartItem(getItem(position));
        holder.cartRowBinding.executePendingBindings();
    }

    public class CartVH extends RecyclerView.ViewHolder{
        CartRowBinding cartRowBinding;
        public CartVH(@NonNull CartRowBinding cartRowBinding){
            super(cartRowBinding.getRoot());
            this.cartRowBinding = cartRowBinding;
        }
    }

}
