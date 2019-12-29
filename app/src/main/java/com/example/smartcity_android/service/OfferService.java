package com.example.smartcity_android.service;

import com.example.smartcity_android.data.model.DTO.OfferDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OfferService {
    @GET("Offer/offerdetails/{offerId}")
    Call<OfferDetails> findOfferById(@Path("offerId") Integer offerId);
}
