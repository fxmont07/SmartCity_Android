package com.example.smartcity_android.data.model;

public class CriterionStudent {
    private Integer criterionId;
    private Integer studentId;
    private Boolean isMandatory;
    private String description;

    public CriterionStudent(Integer criterionId, Integer studentId, Boolean isMandatoy, String description) {
        this.criterionId = criterionId;
        this.studentId = studentId;
        this.isMandatory = isMandatoy;
        this.description = description;
    }

    public Integer getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(Integer criterionId) {
        this.criterionId = criterionId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Boolean getMandatory() {
        return isMandatory;
    }

    public void setMandatory(Boolean mandatory) {
        isMandatory = mandatory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
