package com.example.smartcity_android.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity_android.R;
import com.example.smartcity_android.data.model.DTO.EvaluationDTO;

import java.util.List;

public class EvaluationAdapter extends RecyclerView.Adapter<EvaluationAdapter.EvaluationHolder> {
    private List<EvaluationDTO> evaluations;
    private Context context;

    public static class EvaluationHolder extends RecyclerView.ViewHolder {

        private TextView comment;
        private RatingBar rating;

        public EvaluationHolder(View itemView) {
            super(itemView);
            comment = itemView.findViewById(R.id.TComment);
            rating = itemView.findViewById(R.id.RBRating);
        }
    }

    public EvaluationAdapter(List<EvaluationDTO> evaluations, Context context) {
        this.evaluations = evaluations;
        this.context = context;
    }

    public EvaluationAdapter.EvaluationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_evaluation, parent, false);
        EvaluationAdapter.EvaluationHolder cH = new EvaluationAdapter.EvaluationHolder(v);
        return cH;
    }

    @Override
    public void onBindViewHolder(EvaluationAdapter.EvaluationHolder holder, int position) {
        //holder.comment.setText(evaluations.get(position).getDescription());
        //holder.rating.setRating(evaluations.get(position).getPointOn5());
        holder.rating.setEnabled(false);
        holder.rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //TODO addEvaluation
            }
        });
    }

    @Override
    public int getItemCount() {
        return evaluations.size();
    }
}
