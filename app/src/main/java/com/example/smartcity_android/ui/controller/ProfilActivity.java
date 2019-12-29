package com.example.smartcity_android.ui.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.smartcity_android.R;
import com.example.smartcity_android.DAO.StudentDataAccess;
import com.example.smartcity_android.data.model.DTO.AddressDTO;
import com.example.smartcity_android.data.model.DTO.StudentEditForm;
import com.example.smartcity_android.tool.Tool;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfilActivity extends MenuActivity {

    @BindView(R.id.TInName)
    public TextInputLayout txtInName; // txtIn for TextInput

    @BindView (R.id.TInFirstName)
    public TextInputLayout txtInFirstName;

    @BindView(R.id.TInStreet)
    public TextInputLayout txtInStreet;

    @BindView(R.id.TInStreetNumber)
    public TextInputLayout txtInStreetNumber;

    @BindView(R.id.TInLocality)
    public TextInputLayout txtInLocality;

    @BindView(R.id.TInPostCode)
    public TextInputLayout txtInPostCode;

    @BindView(R.id.TInCountry)
    public TextInputLayout txtInCountry;

    @BindView (R.id.BSave)
    public Button bSave;

    private StudentDataAccess studentDataAccess = new StudentDataAccess(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        ButterKnife.bind(this);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.shared_pref_student), MODE_PRIVATE);
        int studentId = sharedPref.getInt(getString(R.string.id_current_student), -1);

        if(Tool.hasInternet(ProfilActivity.this)) {
            studentDataAccess.findStudentById(studentId, txtInName, txtInFirstName, txtInStreet, txtInStreetNumber, txtInLocality, txtInPostCode, txtInCountry);
        } else {
            Toast.makeText(ProfilActivity.this, R.string.internet, Toast.LENGTH_LONG).show();
        }


        //studentDataAccess.findStudentById(studentId, txtInName, txtInFirstName, txtInStreet, txtInStreetNumber, txtInLocality, txtInPostCode, txtInCountry);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkIndentifiers()) {

                    StudentEditForm editStudent = new StudentEditForm();
                    editStudent.setLastName(txtInName.getEditText().getText().toString());
                    editStudent.setFirstName(txtInFirstName.getEditText().getText().toString());
                    AddressDTO address = new AddressDTO(
                            txtInStreet.getEditText().getText().toString(),
                            txtInStreetNumber.getEditText().getText().toString(),
                            txtInLocality.getEditText().getText().toString(),
                            txtInPostCode.getEditText().getText().toString(),
                            txtInCountry.getEditText().getText().toString()
                    );

                    editStudent.setAddress(address);

                    if(Tool.hasInternet(ProfilActivity.this)) {
                        studentDataAccess.editStudent(studentId,editStudent);
                    } else {
                        Toast.makeText(ProfilActivity.this, R.string.internet, Toast.LENGTH_LONG).show();
                    }

                    //studentDataAccess.editStudent(studentId,editStudent);
                }
            }
        });

        if(savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("name", txtInName.getEditText().getText().toString());
        outState.putString("firstName", txtInFirstName.getEditText().getText().toString());
        outState.putString("street", txtInStreet.getEditText().getText().toString());
        outState.putString("num", txtInStreetNumber.getEditText().getText().toString());
        outState.putString("locality", txtInLocality.getEditText().getText().toString());
        outState.putString("postCode", txtInPostCode.getEditText().getText().toString());
        outState.putString("country", txtInCountry.getEditText().getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        txtInName.getEditText().setText(savedInstanceState.getString("name"));
        txtInFirstName.getEditText().setText(savedInstanceState.getString("firstName"));
        txtInStreet.getEditText().setText(savedInstanceState.getString("street"));
        txtInStreetNumber.getEditText().setText(savedInstanceState.getString("num"));
        txtInLocality.getEditText().setText(savedInstanceState.getString("locality"));
        txtInPostCode.getEditText().setText(savedInstanceState.getString("postCode"));
        txtInCountry.getEditText().setText(savedInstanceState.getString("country"));
    }




    public boolean checkIndentifiers() {
        Context context = getApplicationContext();
        return Tool.hasLengthValid(txtInName, context, 1) &
                Tool.hasLengthValid(txtInFirstName, context, 1) &
                Tool.hasLengthValid(txtInStreet, context, 1) &
                Tool.hasLengthValid(txtInStreetNumber, context, 1) &
                Tool.hasLengthValid(txtInLocality, context, 1) &
                Tool.hasPostCodeValid(txtInPostCode, context) &
                Tool.hasLengthValid(txtInCountry, context, 1)
                ;
    }
}
