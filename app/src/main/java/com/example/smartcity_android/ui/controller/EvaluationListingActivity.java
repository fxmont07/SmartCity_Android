package com.example.smartcity_android.ui.controller;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

// Non terminée avec les appels à l'api
public class EvaluationListingActivity extends AppCompatActivity {
    @BindView(R.id.RVEvaluation)
    public RecyclerView rVEvaluation;

    private RecyclerView.Adapter rVAdapter;
    private RecyclerView.LayoutManager rVLayout;

    //private Test test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_listing);
        ButterKnife.bind(this);

    //test = new Test();
    //int companyId = getIntent().getIntExtra("companyID", -1);
        //test = new Test();
        int companyId = getIntent().getIntExtra("companyID", -1);

        rVEvaluation.setHasFixedSize(true);

        rVLayout = new LinearLayoutManager(this);
        rVEvaluation.setLayoutManager((rVLayout));

        //rVAdapter = new EvaluationAdapter(findEvaluationWithCompanyId(companyId), getApplicationContext());

        rVEvaluation.setAdapter(rVAdapter);
    }

    /*private List<Evaluation> findEvaluationWithCompanyId(int companyId) {
    //TODO
        return test.companies.get(0).getEvalutions();
    }*/
}
