package com.example.smartcity_android.data.model.DTO;

public class OfferDetails {
    private int id;
    private String titleJob;
    private String description;
    private String other;

    private CompanyDetails company;
    private AddressDTO address;


    public OfferDetails() {
    }

    public OfferDetails(int id, String titleJob, String description, String other, CompanyDetails company, AddressDTO address) {
        this.id = id;
        this.titleJob = titleJob;
        this.description = description;
        this.other = other;
        this.company = company;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public CompanyDetails getCompany() {
        return company;
    }

    public void setCompany(CompanyDetails company) {
        this.company = company;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
