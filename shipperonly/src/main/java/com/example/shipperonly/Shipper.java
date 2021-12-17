package com.example.shipperonly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import static com.example.shipperonly.models.RetrofitClient.getRetrofit;

import com.example.shipperonly.InterfaceRepository.Methods;
import com.example.shipperonly.adapters.OrderListAdapter;
import com.example.shipperonly.models.Order;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Shipper extends AppCompatActivity {

    ListView listView;
    OrderListAdapter orderListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipper);
        getOrderList();
    }

    private void getOrderList() {
        Methods methods = getRetrofit().create(Methods.class);
        Call<List<Order>> call = methods.loadOrder();
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                List<Order> data = response.body();
                orderListAdapter = new OrderListAdapter(Shipper.this,R.layout.order);
                listView = findViewById(R.id.listView);
                for(Order dt : data){
                    orderListAdapter.add(dt);
                }
                listView.setAdapter(orderListAdapter);
//                Log.d("log", "onResponse: Success");
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Order dt = (Order)orderListAdapter.getItem(position);
                        Intent intent = new Intent(Shipper.this, Delivery.class);
                        intent.putExtra("deli", dt);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.v("log:", t.getMessage());
            }
        });
    }
}