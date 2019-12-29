package com.example.smartcity_android.data.model.DTO;

public class StudentForm {

    private String Email ;

    private String Password ;

    private String ConfirmPassword ;

    private String LastName ;

    private String FirstName ;

    private AddressDTO Address ;

    private String Section ;


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public AddressDTO getAddress() {
        return Address;
    }

    public void setAddress(AddressDTO address) {
        Address = address;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }
}
