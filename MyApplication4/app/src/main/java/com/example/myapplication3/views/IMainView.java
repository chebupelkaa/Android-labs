package com.example.myapplication3.views;

import com.example.myapplication3.models.Patient;

import java.util.List;

public interface IMainView {
    void showPatients(List<Patient> patients);
    void addPatient();
    void deletePatients();
    void showEditPatient(Patient patient);
    void showByDiagnosis();
    void showByPhone();

}
