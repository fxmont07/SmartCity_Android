package com.example.smartcity_android.ui.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity_android.R;
import com.example.smartcity_android.data.RetrofitFactory;
import com.example.smartcity_android.data.model.CriterionStudent;
import com.example.smartcity_android.data.model.DTO.CriterionStudentDTO;
import com.example.smartcity_android.data.model.DTO.OfferResultMatching;
import com.example.smartcity_android.service.CriterionStudentService;
import com.example.smartcity_android.tool.Tool;
import com.example.smartcity_android.ui.OfferAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OfferActivity extends MenuActivity  {

    @BindView(R.id.RVEvaluation)
    public RecyclerView rVOffer;

    private RecyclerView.Adapter rVAdapter;
    private RecyclerView.LayoutManager rVLayout;

    ArrayList<CriterionStudent> criterionStudents = new ArrayList<>();
    ArrayList<OfferResultMatching> offerResultMatchings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
        ButterKnife.bind(this);

        final Gson gson = new Gson();
        Type criterionsType;

        criterionsType = new TypeToken<ArrayList<CriterionStudent>>(){}.getType();
        String sJson = getIntent().getStringExtra("criterions");
        criterionStudents = gson.fromJson(sJson, criterionsType);

        rVOffer.setHasFixedSize(true);

        rVLayout = new LinearLayoutManager(this);
        rVOffer.setLayoutManager((rVLayout));

        rVAdapter = new OfferAdapter(offerResultMatchings, getApplicationContext(), new OfferAdapter.OnItemClickListener() {
            @Override public void onClick(int offerId) {
                if(Tool.hasInternet(OfferActivity.this)) {
                    Intent intent = new Intent(OfferActivity.this, OfferDetailsActivity.class);
                    intent.putExtra("currentOffer", offerId);
                    startActivity(intent);
                } else {
                    Toast.makeText(OfferActivity.this, R.string.internet, Toast.LENGTH_LONG).show();
                }
            }
        });

        rVOffer.setAdapter(rVAdapter);

        if(Tool.hasInternet(OfferActivity.this)) {
            postMatchCriterions();
        } else {
            Toast.makeText(OfferActivity.this, R.string.internet, Toast.LENGTH_LONG).show();
        }
    }

    public void postMatchCriterions() {
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
                    try {
                        Toast.makeText(OfferActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(OfferActivity.this, R.string.noSucces, Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                    List<OfferResultMatching> offers = response.body();
                    offerResultMatchings.addAll(offers);
                    OfferAdapter adapter = (OfferAdapter) rVAdapter;
                    adapter.setOffers(offerResultMatchings);
                    Objects.requireNonNull(rVOffer.getAdapter()).notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<OfferResultMatching>> call, Throwable t) {
                Toast.makeText(OfferActivity.this, R.string.noSucces, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
