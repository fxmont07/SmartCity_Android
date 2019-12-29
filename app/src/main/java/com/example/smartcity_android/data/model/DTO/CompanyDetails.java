package com.example.smartcity_android.data.model.DTO;

public class CompanyDetails {
    private int id;
    private boolean isPremium;
    private String companyName;
    private String email;
    private float rating;


    public CompanyDetails() {
    }

    public CompanyDetails(int id, boolean isPremium, String companyName, String email, float rating) {
        this.id = id;
        this.isPremium = isPremium;
        this.companyName = companyName;
        this.email = email;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
