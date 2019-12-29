package com.example.smartcity_android.service;

import com.example.smartcity_android.data.model.DTO.StudentDTO;
import com.example.smartcity_android.data.model.DTO.StudentEditForm;
import com.example.smartcity_android.data.model.DTO.StudentForm;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface StudentService {

    @GET("Student/{id}")
    Call<StudentDTO> getById(@Path("id") int studentId);

    @POST("Student")
    Call<StudentDTO> addStudent(@Body StudentForm newStudent);

    @PUT("Student/{studentId}")
    Call<StudentDTO> editStudent(@Path("studentId") int studentId, @Body StudentEditForm studentEdit);

}
