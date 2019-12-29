package com.example.smartcity_android.data.model.DTO;

public class OfferItemList {
    private String titleJob;
    private String description;
    private AddressDTO address;
    private String company;
    private Float rating;
    private Boolean isPremium;

    public OfferItemList(String titleJob, String description, AddressDTO address, String company, Float rating, Boolean isPremium) {
        this.titleJob = titleJob;
        this.description = description;
        this.address = address;
        this.company = company;
        this.rating = rating;
        this.isPremium = isPremium;
    }

    public String getTitleJob() {
        return titleJob;
    }

    public void setTitleJob(String titleJob) {
        this.titleJob = titleJob;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Boolean getPremium() {
        return isPremium;
    }

    public void setPremium(Boolean premium) {
        isPremium = premium;
    }
}
