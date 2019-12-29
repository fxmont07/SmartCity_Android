package com.example.smartcity_android.data.model;

public class Criterion {

    private Integer id;
    private String description;
    private Section section;


    public Criterion(Integer id, String description) {
        this.id = id;

        this.description = description;
    }

    // Criterion from student
    public Criterion(Integer id, String description, Section section) {
        this.id = id;
        this.description = description;
        this.section = section;

    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }


    public Section getSectionId() {
        return section;
    }

}
