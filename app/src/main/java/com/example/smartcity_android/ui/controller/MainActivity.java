package com.example.smartcity_android.ui.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.example.smartcity_android.R;
import com.example.smartcity_android.data.RetrofitFactory;
import com.example.smartcity_android.data.model.DTO.Login;
import com.example.smartcity_android.data.model.DTO.TokenDTO;
import com.example.smartcity_android.dataAccess.service.LoginService;
import com.example.smartcity_android.tool.Tool;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    // State
    @BindView(R.id.TError)
    public TextView txtError;

    @BindView(R.id.TInEmail)
    public TextInputLayout txtInEmail;

    @BindView(R.id.TInPassword)
    public TextInputLayout txtInPassword;

    @BindView(R.id.BForogotPassword)
    public Button buttonForgotPassword;

    @BindView(R.id.BSignUp)
    public Button buttonSignUp;

    @BindView(R.id.BLogIn)
    public Button buttonLogIn;

    // Life Cycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Use ButterKnife Library => auto bind attributs
        ButterKnife.bind(this);

        txtError.setVisibility(View.INVISIBLE);

        buttonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Non implémenté", Toast.LENGTH_LONG).show();
            }
        });

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIndentifiers()) {
                    if (Tool.hasInternet(MainActivity.this)) {
                        findLogin(new Login(
                                Objects.requireNonNull(txtInEmail.getEditText()).getText().toString(),
                                Objects.requireNonNull(txtInPassword.getEditText()).getText().toString()));
                    } else {
                        Toast.makeText(MainActivity.this, R.string.internet, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        TestID();
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("email", Objects.requireNonNull(txtInEmail.getEditText()).getText().toString());
        outState.putString("password", Objects.requireNonNull(txtInPassword.getEditText()).getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Objects.requireNonNull(txtInEmail.getEditText()).setText(savedInstanceState.getString("email"));
        txtInPassword.getEditText().setText(savedInstanceState.getString("password"));
    }

    // Methods
    public boolean checkIndentifiers() {
        Context context = getApplicationContext();
        return Tool.isEmailValid(txtInEmail, context) & Tool.hasLengthValid(txtInPassword, context, 8);
    }

    public void TestID() {
        Objects.requireNonNull(txtInEmail.getEditText()).setText("student@mail.be");
        Objects.requireNonNull(txtInPassword.getEditText()).setText("mdpStudent");
    }

    public void findLogin(Login login) {
        Retrofit retrofit = RetrofitFactory.getIntanceWithoutToken();
        LoginService loginService = retrofit.create(LoginService.class);
        Call<TokenDTO> call = loginService.findLoginRetro(new Login(login.getEmail(), login.getPassword()));
        call.enqueue(new Callback<TokenDTO>() {
            @Override
            public void onResponse(Call<TokenDTO> call, Response<TokenDTO> response) {
                if (!response.isSuccessful()) {
                    try {
                        txtError.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(MainActivity.this, R.string.noSucces, Toast.LENGTH_SHORT).show();
                    }
                    return;
                }

                TokenDTO res = response.body();
                RetrofitFactory.setToken(new TokenDTO(res.accessToken, res.expiresIn));
                JWT jwt = new JWT(res.accessToken);

                Claim claim = jwt.getClaim("UserId");
                int id = claim.asInt();

                SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_student), MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(getString(R.string.id_current_student), id);
                editor.apply();

                Intent intent = new Intent(MainActivity.this, CriterionStudentActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<TokenDTO> call, Throwable t) {
                Toast.makeText(MainActivity.this, R.string.noSucces, Toast.LENGTH_SHORT).show();
            }
        });
    }
}


