package com.example.smartcity_android.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity_android.R;
import com.example.smartcity_android.data.model.DTO.OfferResultMatching;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferHolder> {
    private List<OfferResultMatching> offers;
    private Context context;
    private final OnItemClickListener listener;

    public List<OfferResultMatching> getOffers() {
        return offers;
    }

    public void setOffers(List<OfferResultMatching> offers) {
        this.offers = offers;
    }

    public static class OfferHolder extends RecyclerView.ViewHolder {

        private CircleImageView img;
        private TextView compagnyName;
        private RatingBar ratingBar;
        private TextView title;
        private TextView description;
        private TextView address;

        public OfferHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.IOffer);
            compagnyName = itemView.findViewById(R.id.TCompanyName);
            ratingBar = itemView.findViewById(R.id.RBRating);
            title = itemView.findViewById(R.id.TOfferTitle);
            description = itemView.findViewById(R.id.TOfferDescription);
            address = itemView.findViewById(R.id.TAddress);
        }

        public void bind(int offerId, OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onClick(offerId);
                }
            });
        }
    }

    public OfferAdapter(List<OfferResultMatching> offers, Context context, OnItemClickListener listener) {
        this.offers = offers;
        this.context = context;
        this.listener = listener;
    }

    public OfferAdapter.OfferHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_offer, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO passé à vue details en fonction de la bonne offre
                //Toast.makeText(context, "coucou", Toast.LENGTH_LONG).show();
                Log.i("coucou", "coucou adapt");
            }
        });
        OfferAdapter.OfferHolder oH = new OfferAdapter.OfferHolder(v);
        return oH;
    }

    @Override
    public void onBindViewHolder(OfferAdapter.OfferHolder holder, int position) {
        holder.bind(offers.get(position).getOfferId(), listener);
        holder.img.setBorderWidth(offers.get(position).getPremium() ? 0 : 10);
        holder.img.setBorderColor(Color.YELLOW);
        holder.compagnyName.setText(offers.get(position).getCompany());
        holder.ratingBar.setRating(offers.get(position).getRating());
        holder.ratingBar.setEnabled(false);
        holder.title.setText(offers.get(position).getTitleJob());
        holder.description.setText(offers.get(position).getDescription());
        holder.address.setText(offers.get(position).displayShortAddress());
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    public interface OnItemClickListener {
        void onClick(int offerId);
    }
}
