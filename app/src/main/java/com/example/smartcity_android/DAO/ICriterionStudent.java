package com.example.smartcity_android.DAO;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity_android.data.model.CriterionStudent;
import com.example.smartcity_android.data.model.DTO.OfferResultMatching;
import com.example.smartcity_android.ui.CriterionAdapter;
import com.example.smartcity_android.ui.OfferAdapter;

import java.util.ArrayList;
import java.util.List;

public interface ICriterionStudent {
    void findCriterionStudentByStudentId(int studentId, ArrayList<CriterionStudent> criterionStudents, CriterionAdapter adapter, RecyclerView recyclerView);
    void putCriterions(List<CriterionStudent> criterions);
    void postMatchCriterions(List<CriterionStudent> criterionStudents, ArrayList<OfferResultMatching> offerResultMatchings, OfferAdapter adapter, RecyclerView recyclerView);
}
