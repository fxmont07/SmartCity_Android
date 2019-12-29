package com.example.smartcity_android.service;

import com.example.smartcity_android.data.model.DTO.StudentDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CriterionService {
    @GET("Student/{studentId}")
    Call<StudentDTO> findStudentById(@Path("studentId") Integer studentId);
}
