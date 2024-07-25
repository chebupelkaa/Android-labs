package com.example.myapplication3.utils;

import android.content.Intent;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.contracts.PatientsByDiagnosisActivityContract;
import com.example.myapplication3.contracts.PatientsByPhoneActivityContract;
import com.example.myapplication3.views.AddPatientActivity;

public class ViewLauncher {
    private  AppCompatActivity view;
    private ActivityResultLauncher patientsByDiagnosisLauncher;
    private ActivityResultLauncher patientsByPhoneLauncher;
    private ActivityResultContract contract;
    public ViewLauncher(AppCompatActivity view) {
        this.view=view;

        patientsByDiagnosisLauncher=view.registerForActivityResult(new PatientsByDiagnosisActivityContract(),
                new ActivityResultCallback<Object>() {
                    @Override
                    public void onActivityResult(Object result){

                    }
                }
        );
        patientsByPhoneLauncher = view.registerForActivityResult(new PatientsByPhoneActivityContract(),
                new ActivityResultCallback<Object>() {
            @Override
            public void onActivityResult(Object result){

            }
        });

    }

    public void startPatientsByDiagnosisActivity(){
        patientsByDiagnosisLauncher.launch(null);
    }

    public void startPatientsByPhoneActivity(){
        patientsByPhoneLauncher.launch(null);
    }
    public void startAddPatientActivity(){
        view.startActivity(new Intent(view.getApplicationContext(), AddPatientActivity.class));
    }
}
