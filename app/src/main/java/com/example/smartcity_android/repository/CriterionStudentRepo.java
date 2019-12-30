package com.example.smartcity_android.repository;

import com.example.smartcity_android.data.RetrofitFactory;
import com.example.smartcity_android.data.model.CriterionStudent;
import com.example.smartcity_android.data.model.DTO.CriterionStudentDTO;
import com.example.smartcity_android.dataAccess.service.CriterionStudentService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class CriterionStudentRepo {
    public CriterionStudentRepo() {
    }

    public Call<Void> CritStudentToCritStudentDTO(List<CriterionStudent> criterions) {
        List<CriterionStudentDTO> criterionsDTO = new ArrayList<>();
        for(CriterionStudent c : criterions) {
            criterionsDTO.add(new CriterionStudentDTO(c.getCriterionId(),c.getStudentId(), c.getMandatory() ? 1 : 0, c.getDescription()));
        }
        Retrofit retrofit = RetrofitFactory.getIntanceWithToken();
        CriterionStudentService criterionService = retrofit.create(CriterionStudentService.class);
        return criterionService.putCriterions(criterionsDTO);
    }
}
