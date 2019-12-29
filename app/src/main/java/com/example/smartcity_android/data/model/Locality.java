package com.example.smartcity_android.data.model;

public class Locality {

    private Integer id;
    private String locality;
    private String postCode;
    private String country;

    public Locality(Integer id, String locality, String postCode) {
        this.id = id;
        this.locality = locality;
        this.postCode = postCode;
    }

    public Locality(String locality, String postCode) {
        this.locality = locality;
        this.postCode = postCode;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public String getLocality() {
        return locality;
    }

    public String getPostCode() {
        return postCode;
    }

    /*public String getCountry() {
        return country;
    }*/

    // Methods
    public String displayLocalityAndPostCode() {
        return locality + " - " + postCode;
    }


}
