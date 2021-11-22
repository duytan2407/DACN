package com.example.dacn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androiddoan.Api.ApiClient;
import com.example.androiddoan.Api.Model.NguoiDungModel;
import com.example.androiddoan.Utils.HashMD5;


import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DangNhap extends AppCompatActivity {
    private EditText edtMail, edtPassword;
    private Button btnregister , btnlogin;
    private List<NguoiDungModel> mListUser;
    private  NguoiDungModel mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_dang_nhap);
        getListUsers();
        mListUser = new ArrayList<>();

        edtMail = findViewById(R.id.edtGMail);
        edtPassword = findViewById(R.id.edtMatKhau);
        btnlogin = findViewById(R.id.btnLogin);
        btnregister = findViewById(R.id.btnresgister);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DangNhap.this , DangKy.class);
                startActivity(i);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLogin();

            }
        });

    }

    private void clickLogin() {
        String mail = edtMail.getText().toString().trim();

//        String Password = edtPassword.getText().toString().trim();
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
        for(NguoiDungModel user : mListUser)
        {
            if(mail.equals(user.getEmail()) && Password.equals(user.getPassword()))
            {
                IsHasUser = true;
                mUser = user;
                break;
            }

        }
        if(IsHasUser)
        {
            Intent intent = new Intent(DangNhap.this,TrangChu.class);
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

    private void getListUsers()
    {
        ApiClient.apiclient.getListUsers("IwAR2GA7qf_H1YmrFv_5qka-bbwioEX12iNVWi2WcoJuOK74u54zlBdnEADbY")
                .enqueue(new Callback<List<NguoiDungModel>>() {
                    @Override
                    public void onResponse(Call<List<NguoiDungModel>> call, Response<List<NguoiDungModel>> response) {
                        mListUser = response.body();
                        Log.e("List users", mListUser.size()+"");
                    }

                    @Override
                    public void onFailure(Call<List<NguoiDungModel>> call, Throwable t) {
                        Toast.makeText(DangNhap.this, "Dang Nhap That Bai", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}