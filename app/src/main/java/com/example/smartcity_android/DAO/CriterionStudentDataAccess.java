package com.example.smartcity_android.DAO;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity_android.data.RetrofitFactory;
import com.example.smartcity_android.data.model.CriterionStudent;
import com.example.smartcity_android.data.model.DTO.CriterionStudentDTO;
import com.example.smartcity_android.data.model.DTO.OfferResultMatching;
import com.example.smartcity_android.service.CriterionStudentService;
import com.example.smartcity_android.ui.CriterionAdapter;
import com.example.smartcity_android.ui.OfferAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CriterionStudentDataAccess implements ICriterionStudent {

    @Override
    public void findCriterionStudentByStudentId(int studentId, ArrayList<CriterionStudent> criterionStudents, CriterionAdapter adapter , RecyclerView recyclerView) {
        Retrofit retrofit = RetrofitFactory.getIntanceWithToken();
        CriterionStudentService criterionService = retrofit.create(CriterionStudentService.class);
        Call<List<CriterionStudentDTO>> call = criterionService.getStudentById(studentId);
        call.enqueue(new Callback<List<CriterionStudentDTO>>() {
            @Override
            public void onResponse(Call<List<CriterionStudentDTO>> call, Response<List<CriterionStudentDTO>> response) {
                if (!response.isSuccessful()) {
                    Log.i("student", "no succes");
                    return;
                }
                List<CriterionStudentDTO> criterionStudentDTOS = response.body();
                for(CriterionStudentDTO criterion : criterionStudentDTOS) {
                    criterionStudents.add(new CriterionStudent(
                            criterion.getCriterionId(),
                            criterion.getStudentId(),
                            criterion.isMandatory() == 1,
                            criterion.getDescription()
                    ));
                }
                if(adapter != null && recyclerView != null) {
                    adapter.setCriterions(criterionStudents);
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<CriterionStudentDTO>> call, Throwable t) {
                Log.i("student", "error" + t.getMessage() + "  " + t.getCause());
            }
        });
    }

    @Override
    public void putCriterions(List<CriterionStudent> criterions) {
        List<CriterionStudentDTO> criterionsDTO = new ArrayList<>();
        for(CriterionStudent c : criterions) {
            criterionsDTO.add(new CriterionStudentDTO(c.getCriterionId(),c.getStudentId(), c.getMandatory() ? 1 : 0, c.getDescription()));
        }
        Retrofit retrofit = RetrofitFactory.getIntanceWithToken();
        CriterionStudentService criterionService = retrofit.create(CriterionStudentService.class);
        Call<Void> call = criterionService.putCriterions(criterionsDTO);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Log.i("student", response.message() + " *********************");
                    return;
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("student", "error" + t.getMessage() + "  " + t.getCause());

            }
        });
    }

    @Override
    public void postMatchCriterions(List<CriterionStudent> criterionStudents, ArrayList<OfferResultMatching> offerResultMatchings, OfferAdapter adapter, RecyclerView recyclerView) {
        Retrofit retrofit = RetrofitFactory.getIntanceWithToken();
        CriterionStudentService criterionService = retrofit.create(CriterionStudentService.class);
        ArrayList<CriterionStudentDTO> criterionStudentDTOS = new ArrayList<>();
        for(CriterionStudent criterionStudent : criterionStudents) {
            criterionStudentDTOS.add(new CriterionStudentDTO(
                    criterionStudent.getCriterionId(),
                    criterionStudent.getStudentId(),
                    criterionStudent.getMandatory() ? 1 : 0,
                    criterionStudent.getDescription()
                    )
            );
        }
        Call<List<OfferResultMatching>> call = criterionService.postMatchCriterions(criterionStudentDTOS);
        call.enqueue(new Callback<List<OfferResultMatching>>() {
            @Override
            public void onResponse(Call<List<OfferResultMatching>> call, Response<List<OfferResultMatching>> response) {
                if (!response.isSuccessful()) {
                    Log.i("student", response.message() + " *********************");
                    return;
                }
                List<OfferResultMatching> offers = response.body();
                for(OfferResultMatching offer : offers) {
                    offerResultMatchings.add((offer));
                }

                adapter.setOffers(offerResultMatchings);
                recyclerView.getAdapter().notifyDataSetChanged();
                Log.i("match", "match ok *********" + response.message());
            }

            @Override
            public void onFailure(Call<List<OfferResultMatching>> call, Throwable t) {

            }
        });
    }


}
