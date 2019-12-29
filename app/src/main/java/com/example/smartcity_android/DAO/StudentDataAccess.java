package com.example.smartcity_android.DAO;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.smartcity_android.R;
import com.example.smartcity_android.data.RetrofitFactory;
import com.example.smartcity_android.data.model.DTO.StudentDTO;
import com.example.smartcity_android.data.model.DTO.StudentEditForm;
import com.example.smartcity_android.data.model.DTO.StudentForm;
import com.example.smartcity_android.service.StudentService;
import com.example.smartcity_android.ui.controller.CriterionStudentActivity;
import com.example.smartcity_android.ui.controller.MainActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;


public class StudentDataAccess implements IStudent{

    private Context context;

    public StudentDataAccess(Context context) {
        this.context = context;
    }

    @Override
    public void addStudent(StudentForm newStudent){
        Retrofit retrofit = RetrofitFactory.getIntanceWithoutToken();
        StudentService studentService = retrofit.create(StudentService.class);
        Call<StudentDTO> call = studentService.addStudent(newStudent);
        call.enqueue(new Callback<StudentDTO>() {
            @Override
            public void onResponse(Call<StudentDTO> call, Response<StudentDTO> response) {
                if(!response.isSuccessful()) {
                    try {
                        Toast.makeText(context, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
                    }
                    Log.i("section", "no succes");
                    return;
                }
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<StudentDTO> call, Throwable t) {
                Log.i("section", "error");
            }
        });
    }

    @Override
    public void findStudentById(int studentId, TextInputLayout txtInName, TextInputLayout txtInFirstName, TextInputLayout txtInStreet, TextInputLayout txtInStreetNumber, TextInputLayout txtInLocality, TextInputLayout txtInPostCode, TextInputLayout txtInCountry) {
        Retrofit retrofit = RetrofitFactory.getIntanceWithToken();
        StudentService studentService = retrofit.create(StudentService.class);
        Call<StudentDTO> call = studentService.getById(studentId);
        call.enqueue(new Callback<StudentDTO>() {
            @Override
            public void onResponse(Call<StudentDTO> call, Response<StudentDTO> response) {
                if (!response.isSuccessful()) {
                    try {
                        Toast.makeText(context, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
                    }
                    Log.i("student", "no succes");
                    return;
                }

                if(response.isSuccessful()) {
                    StudentDTO studentResponse = response.body();
                    txtInName.getEditText().setText(studentResponse.getLastName());
                    txtInFirstName.getEditText().setText(studentResponse.getFirstName());
                    txtInStreet.getEditText().setText(studentResponse.getAddress().getStreet());
                    txtInStreetNumber.getEditText().setText(studentResponse.getAddress().getNumber());
                    txtInLocality.getEditText().setText(studentResponse.getAddress().getLocality());
                    txtInPostCode.getEditText().setText(studentResponse.getAddress().getPostCode());
                    txtInCountry.getEditText().setText(studentResponse.getAddress().getCountry());

                }
            }

            @Override
            public void onFailure(Call<StudentDTO> call, Throwable t) {
                Log.i("student", "error");
            }
        });
    }


    @Override
    public void editStudent(int id, StudentEditForm student) {
        Retrofit retrofit = RetrofitFactory.getIntanceWithToken();
        StudentService studentService = retrofit.create(StudentService.class);
        Call<StudentDTO> call = studentService.editStudent(id, student);
        call.enqueue(new Callback<StudentDTO>() {
            @Override
            public void onResponse(Call<StudentDTO> call, Response<StudentDTO> response) {
                if(!response.isSuccessful()){
                    Log.i("editStudent", "no sucess");
                    try {
                        Toast.makeText(context, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
                    }
                    return;
                }

                Intent intent = new Intent(context, CriterionStudentActivity.class);

                SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.shared_pref_student), MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt(context.getString(R.string.id_current_student), id);
                editor.apply();
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<StudentDTO> call, Throwable t) {
                Log.i("editStudent", "error");
            }
        });
    }


}
