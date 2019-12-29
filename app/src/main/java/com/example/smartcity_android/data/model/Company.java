package com.example.smartcity_android.data.model;

import java.util.ArrayList;

public class Company {

    private Integer id;
    private String mail;
    private String password;
    private String name;
    private String phoneNumber;
    private String description;
    private Boolean isPremium;
    private String streetName;
    private String streetNumber;

    private Locality locality;
    private ArrayList<Evaluation> evalutions;


    public Company(Integer id, String mail, String password, String name, String phoneNumber, String description,
                   Boolean isPremium, Locality locality, String streetName, String streetNumber, ArrayList<Evaluation> evalutions) {
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.isPremium = isPremium;
        this.locality = locality;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.evalutions = evalutions;

    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isPremium() {
        return isPremium;
    }

    public Locality getAdresseId() {
        return locality;
    }

    public Boolean getPremium() {
        return isPremium;
    }

    public Locality getLocality() {
        return locality;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public ArrayList<Evaluation> getEvalutions() {
        return evalutions;
    }

    // Methods
    public Float getRating() {
        Float sum = 0F; // Float to use starBar
        for(Evaluation e : evalutions) {
            sum += e.getPointOn5();
        }
        return sum / evalutions.size();
    }

    public String Address() {
        return locality.displayLocalityAndPostCode();
    }
    public void addEvaluation(Evaluation e) {
        evalutions.add(e);
    }

    public void setEvalutions(ArrayList<Evaluation> evalutions) {
        this.evalutions = evalutions;
    }
}
