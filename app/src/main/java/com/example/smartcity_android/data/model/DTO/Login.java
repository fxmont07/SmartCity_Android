package com.example.smartcity_android.data.model.DTO;

public class Login {
    private String Email;
    private String Password;

    public Login(String email, String password) {
        Email = email;
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }


}
