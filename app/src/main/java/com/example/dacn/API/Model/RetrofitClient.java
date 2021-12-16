package com.example.dacn.API.Model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    //    private static String Base_Url = "https://json-api-demooooo.herokuapp.com/";
    private static String Base_Url = "http://10.0.2.2:5000";
    public static Retrofit getRetrofit(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static String getBase_Url() {
        return Base_Url;
    }
}
