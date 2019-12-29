package com.example.smartcity_android.DAO;

import android.widget.RatingBar;
import android.widget.TextView;

public interface IOffer {
    void findOfferById(int id, TextView txtCompanyName, RatingBar ratingBar, TextView txtTitle, TextView txtDescriptionOffer, TextView txtLocation);
}
