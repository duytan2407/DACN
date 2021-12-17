package com.example.shipperonly.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shipperonly.models.Order;
import com.example.shipperonly.R;

import java.util.List;

public class OrderListAdapter extends ArrayAdapter<Order> {
    Activity context;
    int resource;
    TextView txtOrderNumber, txtOrderStatus, txtAddress, txtDate, txtTotal, txtPhone, txtPayment;

    public OrderListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = (Activity) context;
        this.resource= resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View groupView = layoutInflater.inflate(this.resource, null);
        txtOrderNumber = groupView.findViewById(R.id.txtOrderNumber);
        txtOrderStatus = groupView.findViewById(R.id.txtOrderStatus);
        txtAddress = groupView.findViewById(R.id.txtAddress);
        txtDate = groupView.findViewById(R.id.txtDate);
        txtTotal = groupView.findViewById(R.id.txtTotal);
        txtPhone = groupView.findViewById(R.id.txtPhone);
        txtPayment = groupView.findViewById(R.id.txtPayment);
        Order dt = getItem(position);


        txtOrderNumber.setText("Đơn hàng #" + dt.getId());
        String t = String.valueOf(dt.getTrangthai());
        if(t.equalsIgnoreCase("0"))
            txtOrderStatus.setText("Chưa được giao");
        if(t.equalsIgnoreCase("1"))
            txtOrderStatus.setText("Đang giao");
        txtAddress.setText("Địa chỉ: " + dt.getDiachi());
        String s = String.valueOf(dt.getPhuongthucthanhtoan());
        if(s.equalsIgnoreCase("1"))
            txtPayment.setText("Phương thức thanh toán: COD");
        else if (s.equalsIgnoreCase("2"))
            txtPayment.setText("Phương thức thanh toán: CARD");
        txtDate.setText(dt.getNgaygiao());
        txtTotal.setText(dt.getTongtien());
        txtPhone.setText("SĐT: " + dt.getSdt());
        return groupView;
    }
}
