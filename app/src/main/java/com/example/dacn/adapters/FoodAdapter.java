//package com.example.dacn.adapters;
//
//
//import android.app.Activity;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.example.dacn.R;
//import com.example.lib.Model.MenuofLuan;
//
//public class FoodAdapter extends ArrayAdapter<MenuofLuan>{
//    Activity context;
//    int resource;
//    public FoodAdapter(@NonNull Context context, int resource) {
//        super(context, resource);
//        this.context = (Activity) context;
//        this.resource = resource;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater layoutInflater = this.context.getLayoutInflater();
//        View productView = layoutInflater.inflate(this.resource,null);
//
//        ImageView imgFoodPic = productView.findViewById(R.id.imgFoodPic);
//        TextView txtFoodName = productView.findViewById(R.id.txtFoodName);
//        TextView txtFoodPrice = productView.findViewById(R.id.txtFoodPrice);
//        Button btnBuyFood = productView.findViewById(R.id.btnBuyFood);
//
//        MenuofLuan food =getItem(position);
//        imgFoodPic.setImageResource(food.getPic());
//        txtFoodName.setText(food.getName());
//        txtFoodPrice.setText(food.getPrice()+"");
//
//        return productView;
//    }
//}