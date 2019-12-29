package com.example.smartcity_android.service;

import com.example.smartcity_android.data.model.DTO.CriterionStudentDTO;
import com.example.smartcity_android.data.model.DTO.OfferResultMatching;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CriterionStudentService {
    @GET("CriterionStudent/{studentId}")
    Call<List<CriterionStudentDTO>> getStudentById(@Path("studentId") Integer studentId);

    @PUT("CriterionStudent")
    Call<Void> putCriterions(@Body List<CriterionStudentDTO> criterionsStudent);

    @POST("CriterionStudent/match")
    Call<List<OfferResultMatching>> postMatchCriterions(@Body List<CriterionStudentDTO> criterionStudents);
}
