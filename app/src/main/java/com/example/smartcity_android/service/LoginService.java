package com.example.smartcity_android.service;


import com.example.smartcity_android.data.model.DTO.Login;
import com.example.smartcity_android.data.model.DTO.TokenDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface LoginService {

    @POST("Login")
    Call<TokenDTO> findLoginRetro(@Body Login loginDTO);
}
