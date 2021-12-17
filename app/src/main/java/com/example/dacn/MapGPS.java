package com.example.dacn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MapGPS extends AppCompatActivity {
        EditText edthientai, edtdiemden;
        Button btntrack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_gps);

        edthientai = findViewById(R.id.et_hientai);
        edtdiemden = findViewById(R.id.et_destination);
        btntrack = findViewById(R.id.bt_track);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        btntrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String hientai = edthientai.getText().toString().trim();
               String diemden = edtdiemden.getText().toString().trim();

               if(hientai.equals("")&& diemden.equals(""))
               {
                   Toast.makeText(getApplicationContext(), "Nhập Vị Trí", Toast.LENGTH_SHORT).show();
               }else
               {
                   DisplayTrack(hientai,diemden);
               }
            }
        });
    }

    private void DisplayTrack(String hientai, String diemden) {
        try{
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/"+hientai+ "/" + diemden);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (ActivityNotFoundException e)
        {
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            //
            Intent intent   = new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}