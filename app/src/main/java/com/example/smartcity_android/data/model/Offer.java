package com.example.smartcity_android.data.model;

import java.util.ArrayList;

public class Offer {

    private Integer id;
    private String titleJob;
    private String description;
    private String other;
    private Locality locality;

    private Company company;
    private Section section;
    private ArrayList<OfferCriterion> criterions;

    public Offer(Integer id, String titleJob, String description, String other, Locality locality, Company company, Section section) {
        this.id = id;
        this.titleJob = titleJob;
        this.description = description;
        this.other = other;
        this.locality = locality;
        this.company = company;
        this.section = section;

        //Empty

        this.criterions = new ArrayList<>();
    }



    public Integer getId() {
        return id;
    }

    public String getTitleJob() {
        return titleJob;
    }

    public String getDescription() {
        return description;
    }

    public String getOther() {
        return other;
    }

    public Locality getLocality() {
        return locality;
    }

    public Company getCompany() {
        return company;
    }

    public Section getSection() {
        return section;
    }
}
