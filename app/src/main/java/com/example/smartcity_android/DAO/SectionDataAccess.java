package com.example.smartcity_android.DAO;

import android.util.Log;

import com.example.smartcity_android.data.RetrofitFactory;
import com.example.smartcity_android.data.model.DTO.SectionDTO;
import com.example.smartcity_android.service.SectionService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SectionDataAccess implements ISection {

    @Override
    public void findAllSection(List<String> section){
        Retrofit retrofit = RetrofitFactory.getIntanceWithoutToken();
        SectionService sectionService = retrofit.create(SectionService.class);
        Call<List<SectionDTO>> call = sectionService.get();
        call.enqueue(new Callback<List<SectionDTO>>() {
            @Override
            public void onResponse(Call<List<SectionDTO>> call, Response<List<SectionDTO>> response) {
                if(!response.isSuccessful()){
                    Log.i("section", "no succes");
                    return;
                }
                List<SectionDTO> sections = response.body();
                for(SectionDTO sectionDTO : sections)
                    section.add(sectionDTO.getName());
            }

            @Override
            public void onFailure(Call<List<SectionDTO>> call, Throwable t) {
                Log.i("section", "error");
            }
        });
    }
}
