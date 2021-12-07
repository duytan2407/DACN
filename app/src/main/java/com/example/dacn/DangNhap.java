package com.example.dacn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class DangNhap extends AppCompatActivity {
    private EditText edtMail, edtPassword;
    private Button btnregister , btnlogin;
//    private List<NguoiDungModel> mListUser;
//    private  NguoiDungModel mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dang_nhap);
    }
}