package com.example.smartcity_android.ui.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity_android.R;
import com.example.smartcity_android.data.RetrofitFactory;
import com.example.smartcity_android.data.model.DTO.AddressDTO;
import com.example.smartcity_android.data.model.DTO.SectionDTO;
import com.example.smartcity_android.data.model.DTO.StudentDTO;
import com.example.smartcity_android.data.model.DTO.StudentForm;
import com.example.smartcity_android.dataAccess.service.SectionService;
import com.example.smartcity_android.dataAccess.service.StudentService;
import com.example.smartcity_android.tool.Tool;
import com.google.android.material.textfield.TextInputLayout;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.TInEmail)
    public TextInputLayout txtInEmail;

    @BindView(R.id.TInPassword)
    public TextInputLayout txtInPassword;

    @BindView(R.id.TInConfirmPassword)
    public TextInputLayout txtInConfirmPassword;

    @BindView(R.id.TInName)
    public TextInputLayout txtInName;

    @BindView(R.id.TInFirstName)
    public TextInputLayout txtInFirstName;

    @BindView(R.id.TInStreet)
    public TextInputLayout txtInStreet;

    @BindView(R.id.TInStreetNumber)
    public TextInputLayout txtInStreetNumber;

    @BindView(R.id.TInPhone)
    public TextInputLayout txtInPhone;

    @BindView(R.id.TInLocality)
    public TextInputLayout txtInLocality;

    @BindView(R.id.TInPostCode)
    public TextInputLayout txtInPostCode;

    @BindView(R.id.TINCountry)
    public TextInputLayout txtInCountry;

    @BindView(R.id.SSpSection)
    public SearchableSpinner sSpSection;

    @BindView(R.id.BGo)
    public Button bGo;


    private List<String> sectionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter adapter;

        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        if(Tool.hasInternet(SignUpActivity.this)) {
            findAllSection();
        } else {
            Toast.makeText(SignUpActivity.this, R.string.internet, Toast.LENGTH_LONG).show();
        }


        adapter = new ArrayAdapter(SignUpActivity.this, android.R.layout.simple_list_item_1, sectionList);
        sSpSection.setAdapter(adapter);
        sSpSection.setTitle(getString(R.string.section));

        bGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIndentifiers()) {
                    StudentForm newStudent = new StudentForm();
                    newStudent.setEmail(txtInEmail.getEditText().getText().toString());
                    newStudent.setPassword(txtInPassword.getEditText().getText().toString());
                    newStudent.setConfirmPassword(txtInConfirmPassword.getEditText().getText().toString());
                    newStudent.setLastName(txtInName.getEditText().getText().toString());
                    newStudent.setFirstName(txtInFirstName.getEditText().getText().toString());
                    newStudent.setSection(sSpSection.getSelectedItem().toString());
                    AddressDTO address = new AddressDTO();
                    address.setStreet(txtInStreet.getEditText().getText().toString());
                    address.setNumber(txtInStreetNumber.getEditText().getText().toString());
                    address.setLocality(txtInLocality.getEditText().getText().toString());
                    address.setPostCode(txtInPostCode.getEditText().getText().toString());
                    address.setCountry(txtInCountry.getEditText().getText().toString());
                    newStudent.setAddress(address);


                    if(Tool.hasInternet(SignUpActivity.this)) {
                        addStudent(newStudent);
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignUpActivity.this, R.string.internet, Toast.LENGTH_LONG).show();
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
        outState.putString("email", txtInEmail.getEditText().getText().toString());
        outState.putString("password", txtInPassword.getEditText().getText().toString());
        outState.putString("confirmPassword", txtInConfirmPassword.getEditText().getText().toString());
        outState.putString("name", txtInName.getEditText().getText().toString());
        outState.putString("firstName", txtInFirstName.getEditText().getText().toString());
        outState.putString("street", txtInStreet.getEditText().getText().toString());
        outState.putString("num", txtInStreetNumber.getEditText().getText().toString());
        outState.putString("phone", txtInPhone.getEditText().getText().toString());
        outState.putString("locality", txtInLocality.getEditText().getText().toString());
        outState.putString("postCode", txtInPostCode.getEditText().getText().toString());
        outState.putString("country", txtInCountry.getEditText().getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        txtInEmail.getEditText().setText(savedInstanceState.getString("email"));
        txtInPassword.getEditText().setText(savedInstanceState.getString("password"));
        txtInConfirmPassword.getEditText().setText(savedInstanceState.getString("confirmPassword"));
        txtInName.getEditText().setText(savedInstanceState.getString("name"));
        txtInFirstName.getEditText().setText(savedInstanceState.getString("firstName"));
        txtInStreet.getEditText().setText(savedInstanceState.getString("street"));
        txtInStreetNumber.getEditText().setText(savedInstanceState.getString("num"));
        txtInPhone.getEditText().setText(savedInstanceState.getString("phone"));
        txtInLocality.getEditText().setText(savedInstanceState.getString("locality"));
        txtInPostCode.getEditText().setText(savedInstanceState.getString("postCode"));
        txtInCountry.getEditText().setText(savedInstanceState.getString("country"));
    }

    public boolean checkIndentifiers() {
        Context context = getApplicationContext();
        return Tool.isEmailValid(txtInEmail, context) &
                Tool.hasLengthValid(txtInPassword, context, 8) &
                (txtInConfirmPassword.getEditText().getText().toString().compareTo(txtInPassword.getEditText().getText().toString()) == 0) &
                Tool.hasLengthValid(txtInName, context, 1) &
                Tool.hasLengthValid(txtInFirstName, context, 1) &
                Tool.hasLengthValid(txtInStreet, context, 1) &
                Tool.hasLengthValid(txtInStreetNumber, context, 1) &
                Tool.hasLengthValid(txtInPhone, context, 1) &
                Tool.hasLengthValid(txtInLocality, context, 1) &
                Tool.hasPostCodeValid(txtInPostCode, context) &
                Tool.hasLengthValid(txtInCountry, context, 1)
                ;
    }

    public void findAllSection(){
        Retrofit retrofit = RetrofitFactory.getIntanceWithoutToken();
        SectionService sectionService = retrofit.create(SectionService.class);
        Call<List<SectionDTO>> call = sectionService.get();
        call.enqueue(new Callback<List<SectionDTO>>() {
            @Override
            public void onResponse(Call<List<SectionDTO>> call, Response<List<SectionDTO>> response) {
                if (!response.isSuccessful()) {
                    try {
                        Toast.makeText(SignUpActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(SignUpActivity.this, R.string.noSucces, Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                List<SectionDTO> sections = response.body();
                for(SectionDTO sectionDTO : sections)
                    sectionList.add(sectionDTO.getName());
            }

            @Override
            public void onFailure(Call<List<SectionDTO>> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, R.string.noSucces, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addStudent(StudentForm newStudent){
        Retrofit retrofit = RetrofitFactory.getIntanceWithoutToken();
        StudentService studentService = retrofit.create(StudentService.class);
        Call<StudentDTO> call = studentService.addStudent(newStudent);
        call.enqueue(new Callback<StudentDTO>() {
            @Override
            public void onResponse(Call<StudentDTO> call, Response<StudentDTO> response) {
                if (!response.isSuccessful()) {
                    try {
                        Toast.makeText(SignUpActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(SignUpActivity.this, R.string.noSucces, Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
            }

            @Override
            public void onFailure(Call<StudentDTO> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, R.string.noSucces, Toast.LENGTH_SHORT).show();            }
        });
    }
}
