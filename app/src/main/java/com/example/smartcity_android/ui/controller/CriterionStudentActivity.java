package com.example.smartcity_android.ui.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity_android.R;
import com.example.smartcity_android.data.RetrofitFactory;
import com.example.smartcity_android.data.model.CriterionStudent;
import com.example.smartcity_android.data.model.DTO.CriterionStudentDTO;
import com.example.smartcity_android.dataAccess.service.CriterionStudentService;
import com.example.smartcity_android.repository.CriterionStudentRepo;
import com.example.smartcity_android.tool.Tool;
import com.example.smartcity_android.ui.CriterionAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CriterionStudentActivity extends MenuActivity {

    @BindView(R.id.RVEvaluation)
    public RecyclerView rVCriterion;

    private RecyclerView.Adapter rVAdapter;

    @BindView(R.id.BGo)
    public Button bGo;

    ArrayList<CriterionStudent> criterionStudent = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criterion);
        ButterKnife.bind(this);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.shared_pref_student), MODE_PRIVATE);
        int studentId = sharedPref.getInt(getString(R.string.id_current_student), -1);

        rVCriterion.setHasFixedSize(true);

        RecyclerView.LayoutManager rVLayout = new LinearLayoutManager(this);
        rVCriterion.setLayoutManager((rVLayout));

        rVAdapter = new CriterionAdapter(criterionStudent, getApplicationContext(), getString(R.string.modifSaved));
        rVCriterion.setAdapter(rVAdapter);

        if (Tool.hasInternet(CriterionStudentActivity.this)) {
            findCriterionStudentByStudentId(studentId);
        } else {
            Toast.makeText(CriterionStudentActivity.this, R.string.internet, Toast.LENGTH_LONG).show();
        }

        bGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                putCriterions(((CriterionAdapter) rVAdapter).getCriterionsStudent());
                Intent intent = new Intent(CriterionStudentActivity.this, OfferActivity.class);
                String s = gson.toJson(criterionStudent);
                intent.putExtra("criterions", s); //TODO chaine de caractère
                startActivity(intent);
            }
        });
    }

    public void findCriterionStudentByStudentId(int studentId) {
        Retrofit retrofit = RetrofitFactory.getIntanceWithToken();
        CriterionStudentService criterionService = retrofit.create(CriterionStudentService.class);
        Call<List<CriterionStudentDTO>> call = criterionService.getStudentById(studentId);
        call.enqueue(new Callback<List<CriterionStudentDTO>>() {
            @Override
            public void onResponse(Call<List<CriterionStudentDTO>> call, Response<List<CriterionStudentDTO>> response) {
                if (!response.isSuccessful()) {
                    try {
                        Toast.makeText(CriterionStudentActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(CriterionStudentActivity.this, R.string.noSucces, Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                List<CriterionStudentDTO> criterionStudentDTOS = response.body();
                for (CriterionStudentDTO criterion : criterionStudentDTOS) {
                    criterionStudent.add(new CriterionStudent(
                            criterion.getCriterionId(),
                            criterion.getStudentId(),
                            criterion.isMandatory() == 1,
                            criterion.getDescription()
                    ));
                }
                CriterionAdapter adapter = (CriterionAdapter) rVAdapter;
                adapter.setCriterions(criterionStudent);
                Objects.requireNonNull(rVCriterion.getAdapter()).notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CriterionStudentDTO>> call, Throwable t) {
                Toast.makeText(CriterionStudentActivity.this, R.string.noSucces, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void putCriterions(List<CriterionStudent> criterions) {
        CriterionStudentRepo repo = new CriterionStudentRepo();
        Call<Void> call = repo.CritStudentToCritStudentDTO(criterions);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    try {
                        Toast.makeText(CriterionStudentActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(CriterionStudentActivity.this, R.string.noSucces, Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                Toast.makeText(CriterionStudentActivity.this, R.string.save, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CriterionStudentActivity.this, R.string.noSucces, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
