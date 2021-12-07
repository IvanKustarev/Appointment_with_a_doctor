package com.example.appointment_with_a_doctor.db;


import com.example.appointment_with_a_doctor.Doctor;

public interface DoctorRepository {

//    Iterable<Doctor> findAllDoctors();

    Doctor saveDoctor(Doctor doctor);

//    Doctor findDoctorById(Long id);

    Doctor findDoctorByUsername(String username);
}
