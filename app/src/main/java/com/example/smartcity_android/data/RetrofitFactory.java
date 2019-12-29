package com.example.smartcity_android.data;

import com.example.smartcity_android.data.model.Token;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// TODO Devrait être dans une classe global à l'application
public class RetrofitFactory {
    public  static final String BASE_URL = "https://smartcitycarofx.azurewebsites.net/";

    private static Retrofit retrofit;
    private static Token token;

    private static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request newRequest  = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            return chain.proceed(newRequest);
        }
    }).build();

    public static Retrofit getIntanceWithoutToken() {
        if(retrofit ==  null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getIntanceWithToken() {
        if(retrofit !=  null) {
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static void setToken(Token token) {
        RetrofitFactory.token = token;
    }

    public static Token getToken() {
        return token;
    }
}
