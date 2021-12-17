package com.example.dacn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.dacn.API.Model.User;

public class TrangChu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setTitleToolbar();
        getDataIntent();

        Bundle bundleReceive = getIntent().getExtras();
        if(bundleReceive !=null)
        {
            User user = (User) bundleReceive.get("Object User");
            if( user != null)
            {

            }
        }
    }
    private void getDataIntent()
    {
        String strPhoneNumber= getIntent().getStringExtra("phone number");


    }

    private void setTitleToolbar(){
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle("Trang Chu");
        }
    }
    public void click(View view) {
        Intent intent = new Intent(TrangChu.this, MonAn.class);
        startActivity(intent);
    }
}