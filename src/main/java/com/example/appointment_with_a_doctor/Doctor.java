package com.example.appointment_with_a_doctor;


//import org.hibernate.validator.constraints.NotEmpty;

public class Doctor {

//    private Long id;
    private String email;
    private String password;
//    @NotEmpty(message = "Last Name is required.")
    private String lastName;
//    @NotEmpty(message = "First Name is required.")
    private String firstName;
    private String secondName;
    private String specialization;
    private String workingDays;
//    @NotEmpty(message = "Working Hours is required.")
    private String workingHours;
    private String photo;


    public Doctor() {
    }

    public Doctor(String lastName, String firstName, String secondName, String specialization, String workingDays, String workingHours, String photo) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.specialization = specialization;
        this.workingDays = workingDays;
        this.workingHours = workingHours;
        this.photo = photo;

    }

//    public Long getId() {
//        return this.id;
//    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getSecondName() {
        return this.secondName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {return this.email;}

    public String getPassword() {
        return this.password;
    }

    public String getSpecialization() {
        return this.specialization;
    }

    public String getWorkingDays() {
        return this.workingDays;
    }

    public String getWorkingHours() {
        return this.workingHours;
    }

    public String getPhoto() {
        return this.photo;
    }


//    public void setId(Long id) {
//        this.id = id;
//    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setWorkingDays(String workingDays) {
        this.workingDays = workingDays;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    @Override
    public String toString() {
        return this.firstName + " "+ this.lastName;
    }

}

