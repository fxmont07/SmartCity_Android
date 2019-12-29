package com.example.smartcity_android.DAO;

import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.smartcity_android.data.RetrofitFactory;
import com.example.smartcity_android.data.model.DTO.OfferDetails;
import com.example.smartcity_android.service.OfferService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OfferDataAccess implements IOffer {

    @Override
    public void findOfferById(int id, TextView txtCompanyName, RatingBar ratingBar, TextView txtTitle, TextView txtDescriptionOffer, TextView txtLocation) {
        Retrofit retrofit = RetrofitFactory.getIntanceWithToken();
        OfferService offerService = retrofit.create(OfferService.class);
        Call<OfferDetails> call = offerService.findOfferById(id);
        call.enqueue(new Callback<OfferDetails>() {
            @Override
            public void onResponse(Call<OfferDetails> call, Response<OfferDetails> response) {
                if(!response.isSuccessful()){
                    Log.i("offer", "no succes");
                    return;
                }
                OfferDetails offerDetails = response.body();
                txtCompanyName.setText(offerDetails.getCompany().getCompanyName());
                ratingBar.setRating(offerDetails.getCompany().getRating());
                txtTitle.setText(offerDetails.getTitleJob());
                txtDescriptionOffer.setText(offerDetails.getDescription());
                txtLocation.setText(offerDetails.getAddress().displayAddress());
            }

            @Override
            public void onFailure(Call<OfferDetails> call, Throwable t) {

            }
        });
    }
}

// Nous ne somme pas censé passer des objets de la vue pour remplir nos champs.
// Cause : pas d'observer via les livedata et mauvaise compréhension de la strucutre d'un app android
