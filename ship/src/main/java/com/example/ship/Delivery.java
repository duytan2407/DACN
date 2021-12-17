package com.example.ship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ship.models.Order;


public class Delivery extends AppCompatActivity {

    TextView txtAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        txtAddress = findViewById(R.id.txtAddress);

        Intent intent = getIntent();
        Order dt = (Order)intent.getSerializableExtra("deli");
        txtAddress.setText(dt.getDiachi());
    }
}