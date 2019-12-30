package com.example.smartcity_android.ui.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.smartcity_android.R;
import com.example.smartcity_android.data.RetrofitFactory;
import com.example.smartcity_android.data.model.DTO.AddressDTO;
import com.example.smartcity_android.data.model.DTO.StudentDTO;
import com.example.smartcity_android.data.model.DTO.StudentEditForm;
import com.example.smartcity_android.dataAccess.service.StudentService;
import com.example.smartcity_android.tool.Tool;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfilActivity extends MenuActivity {

    @BindView(R.id.TInName)
    public TextInputLayout txtInName;

    @BindView(R.id.TInFirstName)
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

    @BindView(R.id.BSave)
    public Button bSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        ButterKnife.bind(this);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.shared_pref_student), MODE_PRIVATE);
        int studentId = sharedPref.getInt(getString(R.string.id_current_student), -1);

        if (Tool.hasInternet(ProfilActivity.this)) {
            findStudentById(studentId);
        } else {
            Toast.makeText(ProfilActivity.this, R.string.internet, Toast.LENGTH_LONG).show();
        }

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIndentifiers()) {

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

                    if (Tool.hasInternet(ProfilActivity.this)) {
                        editStudent(studentId, editStudent);
                        Intent intent = new Intent(ProfilActivity.this, CriterionStudentActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ProfilActivity.this, R.string.internet, Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(getString(R.string.name), txtInName.getEditText().getText().toString());
        outState.putString(getString(R.string.firstName), txtInFirstName.getEditText().getText().toString());
        outState.putString(getString(R.string.street), txtInStreet.getEditText().getText().toString());
        outState.putString(getString(R.string.streetNumber), txtInStreetNumber.getEditText().getText().toString());
        outState.putString(getString(R.string.locality), txtInLocality.getEditText().getText().toString());
        outState.putString(getString(R.string.postCode), txtInPostCode.getEditText().getText().toString());
        outState.putString(getString(R.string.country), txtInCountry.getEditText().getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        txtInName.getEditText().setText(savedInstanceState.getString(getString(R.string.name)));
        txtInFirstName.getEditText().setText(savedInstanceState.getString(getString(R.string.firstName)));
        txtInStreet.getEditText().setText(savedInstanceState.getString(getString(R.string.street)));
        txtInStreetNumber.getEditText().setText(savedInstanceState.getString(getString(R.string.streetNumber)));
        txtInLocality.getEditText().setText(savedInstanceState.getString(getString(R.string.country)));
        txtInPostCode.getEditText().setText(savedInstanceState.getString(getString(R.string.postCode)));
        txtInCountry.getEditText().setText(savedInstanceState.getString(getString(R.string.country)));
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

    public void findStudentById(int studentId) {
        Retrofit retrofit = RetrofitFactory.getIntanceWithToken();
        StudentService studentService = retrofit.create(StudentService.class);
        Call<StudentDTO> call = studentService.getById(studentId);
        call.enqueue(new Callback<StudentDTO>() {
            @Override
            public void onResponse(Call<StudentDTO> call, Response<StudentDTO> response) {
                if (!response.isSuccessful()) {
                    try {
                        Toast.makeText(ProfilActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(ProfilActivity.this, R.string.noSuccess, Toast.LENGTH_LONG).show();
                    }
                    return;
                }
                StudentDTO studentResponse = response.body();
                txtInName.getEditText().setText(studentResponse.getLastName());
                txtInFirstName.getEditText().setText(studentResponse.getFirstName());
                txtInStreet.getEditText().setText(studentResponse.getAddress().getStreet());
                txtInStreetNumber.getEditText().setText(studentResponse.getAddress().getNumber());
                txtInLocality.getEditText().setText(studentResponse.getAddress().getLocality());
                txtInPostCode.getEditText().setText(studentResponse.getAddress().getPostCode());
                txtInCountry.getEditText().setText(studentResponse.getAddress().getCountry());
            }

            @Override
            public void onFailure(Call<StudentDTO> call, Throwable t) {
                Toast.makeText(ProfilActivity.this, R.string.noSuccess, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void editStudent(int id, StudentEditForm student) {
        Retrofit retrofit = RetrofitFactory.getIntanceWithToken();
        StudentService studentService = retrofit.create(StudentService.class);
        Call<StudentDTO> call = studentService.editStudent(id, student);
        call.enqueue(new Callback<StudentDTO>() {
            @Override
            public void onResponse(Call<StudentDTO> call, Response<StudentDTO> response) {
                if (!response.isSuccessful()) {
                    try {
                        Toast.makeText(ProfilActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(ProfilActivity.this, R.string.noSuccess, Toast.LENGTH_LONG).show();
                    }
                    return;
                }
            }

            @Override
            public void onFailure(Call<StudentDTO> call, Throwable t) {
                Toast.makeText(ProfilActivity.this, R.string.noSuccess, Toast.LENGTH_LONG).show();
            }
        });
    }
}
