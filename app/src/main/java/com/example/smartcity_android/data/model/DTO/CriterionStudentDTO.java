package com.example.smartcity_android.data.model.DTO;

public class CriterionStudentDTO {
    private Integer criterionId;
    private Integer studentId;
    private Integer isMandatory;
    private String description;

    public CriterionStudentDTO(Integer criterionId, Integer studentId, Integer isMandatory, String description) {
        this.criterionId = criterionId;
        this.studentId = studentId;
        this.isMandatory = isMandatory;
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

    public Integer isMandatory() {
        return isMandatory;
    }

    public void setMandatory(Integer mandatory) {
        isMandatory = mandatory;
    }

    public Integer getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Integer isMandatory) {
        this.isMandatory = isMandatory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
