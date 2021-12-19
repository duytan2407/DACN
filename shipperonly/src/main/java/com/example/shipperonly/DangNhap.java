package com.example.shipperonly;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.shipperonly.Client.Model.ApiClient;
import com.example.shipperonly.Client.Model.User;
import com.example.shipperonly.Utils.HashMD5;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhap extends AppCompatActivity {
    private EditText edtMail, edtPassword;
    private Button btnregister, btnlogin;
    private TextView tvdangki;
    private List<User> mListUser;
    private User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dang_nhap);
        getListUsers();

        mListUser = new ArrayList<>();

        edtMail = findViewById(R.id.inputEmail);
        edtPassword = findViewById(R.id.inputPassword);
        btnlogin = findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                clickLogin();
            }
        });
        tvdangki = findViewById(R.id.textViewSignUp);

        tvdangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DangNhap.this , DangKy.class);
                startActivity(i);
            }
        });
    }
    private void getListUsers()
    {
        ApiClient.apiclient.getListUsers()
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                      mListUser  = response.body();
//                      Log.e("list users",mListUser.size()+"");
                        Log.d("log", "onResponse: Success");
                        Toast.makeText(DangNhap.this, "Call Seccc", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(DangNhap.this, "Call err", Toast.LENGTH_SHORT).show();
                    }
                });
//        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
//        Call<List<User>> call = methods.getListUsers();
//        call.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                List<User> data = response.body();
//                for (User dt : data) {
//                    Log.v("log:", dt.getName());
//                    Log.d("log", "onResponse: Success");
//                }
//            }
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//                Log.d("Error", t.toString());
//            }
//        });
    }

    private void clickLogin() {
        getListUsers();
        String mail = edtMail.getText().toString().trim();
       // String Password = edtPassword.getText().toString().trim();
        String Password = HashMD5.MD5(edtPassword.getText().toString().trim());
        if(mail.isEmpty())
        {
            edtMail.setError("Ten Nguoi Dung");
            edtMail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches())
        {
            edtMail.setError("Email Không Tồn Tại");
            edtMail.requestFocus();
            return;

        }
        if(Password.isEmpty())
        {
            edtPassword.setError("Nhap Mat Khau");
            edtPassword.requestFocus();
            return;
        }
        if(Password.length()<5)
        {
            edtPassword.setError("Mat Khau Qua Ngan");
            edtPassword.requestFocus();
            return;
        }
        else
        {
            doLogin(mail,Password);
        }
        boolean IsHasUser = false;
        for( User user : mListUser)
        {
            if(mail.equals(user.getEmail()) && Password.equals(user.getPassword())  )
            {
                IsHasUser = true;
                mUser = user;
                break;
            }
        }
        if(IsHasUser)
        {
            Intent intent = new Intent(DangNhap.this,Shipper.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Object User",mUser);
            intent.putExtras(bundle);
            startActivity(intent);

        }
        else
        {
            Toast.makeText(DangNhap.this, "Email Hoac Mat Khau Sai", Toast.LENGTH_SHORT).show();
        }
    }
    private void doLogin(String mail, String password) {

    }



}