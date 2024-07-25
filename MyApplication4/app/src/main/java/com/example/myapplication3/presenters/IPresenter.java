package com.example.myapplication3.presenters;

import com.example.myapplication3.models.Patient;

public interface IPresenter {
    void onStart();
    void onShowByDiagnosisClicked();
    void onShowByPhoneClicked();
    void onAddPatientClicked();
    void onDestroy(Patient patient);
    void onEditPatientClicked(Patient patient);
}
