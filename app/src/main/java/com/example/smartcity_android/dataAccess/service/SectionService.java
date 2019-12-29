package com.example.smartcity_android.dataAccess.service;

import com.example.smartcity_android.data.model.DTO.SectionDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SectionService {

    @GET("Section")
    Call<List<SectionDTO>> get();
}
