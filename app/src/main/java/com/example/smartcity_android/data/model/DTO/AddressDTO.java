package com.example.smartcity_android.data.model.DTO;

public class AddressDTO {
    private int id;
    private String street;
    private String number;
    private String locality;
    private String postCode;
    private String country;

    public AddressDTO() {
    }

    public AddressDTO(String street, String number, String locality, String postCode, String country) {
        setStreet(street);
        setNumber(number);
        setLocality(locality);
        setPostCode(postCode);
        setCountry(country);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String displayAddress() {
        return country.substring(0,2) + " - " + postCode + ", " + locality + "\n" +
                street + ", " + number;
    }
}
