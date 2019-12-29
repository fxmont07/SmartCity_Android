package com.example.smartcity_android.data.model.DTO;

public class StudentEditForm {


    private String LastName;

    private String FirstName;

    private AddressDTO Address;

    private String SectionName;

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

    public String getSectionName() {
        return SectionName;
    }

    public void setSectionName(String sectionName) {
        SectionName = sectionName;
    }
}
