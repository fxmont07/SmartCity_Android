package com.example.smartcity_android.data.model;

public class Evaluation {

    public enum Source {
        STUDENT,
        COMPANY,
    }

    private Integer id;
    private Integer pointOn5;
    private String description;
    private Source source;
    private String companyName;
    private String student;

    public Evaluation(Integer id, Integer pointOn5, String description, Source source, String companyName, String student) {
        this.id = id;
        this.pointOn5 = pointOn5;
        this.description = description;
        this.source = source;
        this.companyName = companyName;
        this.student = student;
    }

    public Evaluation(Integer id, Integer pointOn5, String description) {
        this.id = id;
        this.pointOn5 = pointOn5;
        this.description = description;
    }



    public Integer getId() {
        return id;
    }

    public Integer getPointOn5() {
        return pointOn5;
    }

    public String getDescription() {
        return description;
    }


}
