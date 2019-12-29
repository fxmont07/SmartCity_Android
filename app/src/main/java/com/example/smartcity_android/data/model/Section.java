package com.example.smartcity_android.data.model;

import java.util.ArrayList;

public class Section {

    private Integer id;
    private String name;

    private ArrayList<Student> students;
    private ArrayList<Offer> offers;
    private ArrayList<Criterion> criterions;

    public Section(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Section(Integer id, String name, ArrayList<Criterion> criterions) {
        this.id = id;
        this.name = name;
        this.criterions = criterions;
    }

    // Create student after find retro
    public Section(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Criterion> getCriterions() {
        return criterions;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addOffers(Offer offer) {
        offers.add(offer);
    }

    public void setCriterions(ArrayList<Criterion> criterions) {
        this.criterions = criterions;
    }

    public void setName(String name) {
        this.name = name;
    }
}
