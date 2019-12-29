package com.example.smartcity_android.DAO;

import com.example.smartcity_android.data.model.DTO.StudentEditForm;
import com.example.smartcity_android.data.model.DTO.StudentForm;
import com.google.android.material.textfield.TextInputLayout;

public interface IStudent {

    void addStudent(StudentForm newStudent);

    void editStudent(int id, StudentEditForm student);

    void findStudentById(int studentId, TextInputLayout txtInName, TextInputLayout txtInFirstName, TextInputLayout txtInStreet, TextInputLayout txtInStreetNumber, TextInputLayout txtInLocality, TextInputLayout txtInPostCode, TextInputLayout txtInCountry);
}
