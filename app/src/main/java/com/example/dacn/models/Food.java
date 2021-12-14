package com.example.dacn.models;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.Objects;

public class Food implements Serializable {
    private String TenMonAn;

    private boolean TinhTrang;

    private String TTChiTiet;

    private String id;

    private String HinhAnh;

    private Double Gia;

    public Food() {
    }

    public Food(String tenMonAn, boolean tinhTrang, String TTChiTiet, String id, String hinhAnh, Double gia) {
        TenMonAn = tenMonAn;
        TinhTrang = tinhTrang;
        this.TTChiTiet = TTChiTiet;
        this.id = id;
        HinhAnh = hinhAnh;
        Gia = gia;
    }

    public String getTenMonAn ()
    {
        return TenMonAn;
    }

    public void setTenMonAn (String TenMonAn)
    {
        this.TenMonAn = TenMonAn;
    }


    public String getTTChiTiet ()
    {
        return TTChiTiet;
    }

    public void setTTChiTiet (String TTChiTiet)
    {
        this.TTChiTiet = TTChiTiet;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getHinhAnh ()
    {
        return HinhAnh;
    }

    public void setHinhAnh (String HinhAnh)
    {
        this.HinhAnh = HinhAnh;
    }

    public boolean isTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public Double getGia() {
        return Gia;
    }

    public void setGia(Double gia) {
        Gia = gia;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [TenMonAn = "+TenMonAn+", TinhTrang = "+TinhTrang+", TTChiTiet = "+TTChiTiet+", id = "+id+", HinhAnh = "+HinhAnh+", Gia = "+Gia+"]";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return isTinhTrang() == food.isTinhTrang() && getTenMonAn().equals(food.getTenMonAn()) && getTTChiTiet().equals(food.getTTChiTiet()) && getId().equals(food.getId()) && getHinhAnh().equals(food.getHinhAnh()) && getGia().equals(food.getGia());
    }

    public static DiffUtil.ItemCallback<Food> itemCallback = new DiffUtil.ItemCallback<Food>() {
        @Override
        public boolean areItemsTheSame(@NonNull Food oldItem, @NonNull Food newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Food oldItem, @NonNull Food newItem) {
            return oldItem.equals(newItem);
        }
    };

    @BindingAdapter("android:foodImage")
    public static  void loadImage(ImageView imageView, String imageURL ){
        Glide.with(imageView)
                .load(imageURL)
                .fitCenter()
                .into(imageView);
    }

}

