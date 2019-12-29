package com.example.smartcity_android.data.model;

import com.example.smartcity_android.data.model.DTO.AddressDTO;

import java.util.ArrayList;

public class Student {

    private Integer id;
    private String email;
    private String passeword;

    private AddressDTO address;
    private String lastName;
    private String firstName;
    private String streetName;
    private String streetNumber;

    private Locality locality;
    private Section section;
    private ArrayList<CriterionStudent> criterions;

    // Test
    public Student() {
    }

    // Edit profil 23/12/19
    public Student(String lastName, String firstName, String streetName, String streetNumber, Locality locality, Section section) {
        this.lastName = lastName;
        this.firstName = firstName;

        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.locality = locality;
        this.section = section;
    }

    public Student(ArrayList<CriterionStudent> criterions) {
        this.criterions = criterions;
    }

    public Student(Integer id, String email, String passeword, String lastName, String firstName, String streetName,
                   String streetNumber , Locality locality, Section section,
                   ArrayList<CriterionStudent> criterions) {
        this.id = id;
        this.email = email;
        this.passeword = passeword;
        this.lastName = lastName;
        this.firstName = firstName;


        this.streetName = streetName;
        this.streetNumber = streetNumber;

        this.locality = locality;
        this.section = section;
        this.criterions = criterions;
    }

    // Used to create a new student with sign up activity
    public Student(String email, String passeword, String lastName, String firstName, Locality locality, String streetName, String streetNumber,
                    Section section, ArrayList<CriterionStudent> criterions) {
        this.email = email;
        this.passeword = passeword;
        this.lastName = lastName;
        this.firstName = firstName;

        this.locality = locality;
        this.streetName = streetName;
        this.streetNumber = streetNumber;

        this.section = section;
        this.criterions = criterions;
    }
    public Student(Integer id, String email, String passeword, String lastName, String firstName, Locality locality, String streetName, String streetNumber,
                   Section section, ArrayList<CriterionStudent> criterions) {
        this.id = id;
        this.email = email;
        this.passeword = passeword;
        this.lastName = lastName;
        this.firstName = firstName;
        this.locality = locality;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.section = section;
        this.criterions = criterions;

    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasseword() {
        return passeword;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Locality getLocality() {
        return locality;
    }

    public Section getSection() {
        return section;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public ArrayList<CriterionStudent> getCriterions() {
        return criterions;
    }

    public void setCriterions(ArrayList<CriterionStudent> criterions) {
        this.criterions = criterions;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
