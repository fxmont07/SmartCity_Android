package com.example.smartcity_android.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity_android.R;
import com.example.smartcity_android.data.model.CriterionStudent;

import java.util.List;

public class CriterionAdapter extends RecyclerView.Adapter<CriterionAdapter.CriterionHolder> {
    private List<CriterionStudent> criterions;
    private Context context;
    private String saved;

    public void setCriterions(List<CriterionStudent> criterions) {
        this.criterions = criterions;
    }

    public List<CriterionStudent> getCriterionsStudent() {
        return criterions;
    }

    public static class CriterionHolder extends RecyclerView.ViewHolder {

        private TextView labelCriterion;
        private RadioGroup radioGroup;
        private RadioButton rBMandatory;

        private RadioButton rBForbidden;

        public CriterionHolder(View itemView) {
            super(itemView);
            labelCriterion = itemView.findViewById(R.id.TLabel);
            radioGroup = itemView.findViewById(R.id.RGCriterionGroup);
            rBMandatory = itemView.findViewById(R.id.RBMandatory);
            rBForbidden = itemView.findViewById(R.id.RBForbidden);
        }
    }

    public CriterionAdapter(List<CriterionStudent> criterions, Context context, String saved) {
        this.criterions = criterions;
        this.context = context;
        this.saved = saved;
        System.out.println("rec" + " " + criterions.size() + " ************");
    }

    public CriterionAdapter.CriterionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_criterion, parent, false);
        CriterionHolder cH = new CriterionHolder(v);
        return cH;
    }

    @Override
    public void onBindViewHolder(CriterionHolder holder, int position) {
        holder.labelCriterion.setText(criterions.get(position).getDescription()); // TODO
        holder.rBMandatory.setChecked(criterions.get(position).getMandatory());
        holder.rBForbidden.setChecked(!criterions.get(position).getMandatory());

        holder.rBMandatory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criterions.get(position).setMandatory(true);
            }
        });


        holder.rBForbidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criterions.get(position).setMandatory(false);
            }
        });


    }

    @Override
    public int getItemCount() {
        return criterions.size();
    }


}
