package com.example.smartcity_android.ui.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity_android.R;
import com.example.smartcity_android.data.RetrofitFactory;
import com.example.smartcity_android.data.model.DTO.OfferDetails;
import com.example.smartcity_android.dataAccess.service.OfferService;
import com.example.smartcity_android.tool.Tool;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OfferDetailsActivity extends MenuActivity {
    @BindView(R.id.IOffer)
    public CircleImageView imgOffer;

    @BindView(R.id.TCompanyName)
    public TextView txtCompanyName;

    @BindView(R.id.RBRating)
    public RatingBar ratingBar;

    @BindView(R.id.TOfferTitleTitle)
    public TextView txtTitleForTitleOffer;

    @BindView(R.id.TOfferTitle)
    public TextView txtTitle;

    @BindView(R.id.TOfferDescriptionTitle)
    public TextView txtTitleForDescriptionOffer;

    @BindView(R.id.TOfferDescription)
    public TextView txtDescriptionOffer;

    @BindView(R.id.TLocationTitle)
    public TextView txtTitleLocality;

    @BindView(R.id.TLocation)
    public TextView txtLocation;

    @BindView(R.id.BContact)
    public Button bContact;

    @BindView(R.id.BEvaluate)
    public Button bEvaluate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_details);
        ButterKnife.bind(this);

        int idOffer = getIntent().getIntExtra(getString(R.string.currentOffer), -1);
        ratingBar.setEnabled(false);

        if(Tool.hasInternet(OfferDetailsActivity.this)) {
            findOfferById(idOffer);
        } else {
            Toast.makeText(OfferDetailsActivity.this, R.string.internet, Toast.LENGTH_LONG).show();
        }



        bContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent  = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", offer.getCompany().getEmail(), null));
                startActivity(Intent.createChooser(intent, getString(R.string.mailTitle)));*/
                Toast.makeText(OfferDetailsActivity.this, getString(R.string.nonimplemented), Toast.LENGTH_LONG).show();
            }
        });

        bEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(OfferDetailsActivity.this, EvaluateActivity.class);
                intent.putExtra("companyId", offer.getCompany().getId());
                startActivity(intent);*/
                Toast.makeText(OfferDetailsActivity.this, getString(R.string.nonimplemented), Toast.LENGTH_LONG).show();
            }
        });

        txtCompanyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(OfferDetailsActivity.this, EvaluationListingActivity.class);
                intent.putExtra("companyId", offer.getCompany().getId());
                startActivity(intent);*/
                Toast.makeText(OfferDetailsActivity.this, getString(R.string.nonimplemented), Toast.LENGTH_LONG).show();
            }
        });

        txtLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(OfferDetailsActivity.this, MapActivity.class);
                startActivity(intent);*/
                Toast.makeText(OfferDetailsActivity.this, getString(R.string.nonimplemented), Toast.LENGTH_LONG).show();
            }
        });
    }


    public void findOfferById(int id) {
        Retrofit retrofit = RetrofitFactory.getIntanceWithToken();
        OfferService offerService = retrofit.create(OfferService.class);
        Call<OfferDetails> call = offerService.findOfferById(id);
        call.enqueue(new Callback<OfferDetails>() {
            @Override
            public void onResponse(Call<OfferDetails> call, Response<OfferDetails> response) {
                if (!response.isSuccessful()) {
                    try {
                        Toast.makeText(OfferDetailsActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(OfferDetailsActivity.this, R.string.noSucces, Toast.LENGTH_SHORT).show();
                    }
                    return;
                }

                OfferDetails offerDetails = response.body();
                txtCompanyName.setText(offerDetails.getCompany().getCompanyName());
                ratingBar.setRating(offerDetails.getCompany().getRating());
                txtTitle.setText(offerDetails.getTitleJob());
                txtDescriptionOffer.setText(offerDetails.getDescription());
                imgOffer.setBorderWidth(offerDetails.getCompany().isPremium() ? 10 : 0);
                txtLocation.setText(offerDetails.getAddress().displayAddress());

                bContact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent;
                        intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", offerDetails.getCompany().getEmail(), null));
                        startActivity(Intent.createChooser(intent, getString(R.string.mailTitle)));
                    }
                });
            }

            @Override
            public void onFailure(Call<OfferDetails> call, Throwable t) {
                Toast.makeText(OfferDetailsActivity.this, R.string.noSucces, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

