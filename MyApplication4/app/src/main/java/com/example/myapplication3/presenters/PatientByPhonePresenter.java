package com.example.myapplication3.presenters;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.myapplication3.models.Patient;
import com.example.myapplication3.repositories.IRepository;
import com.example.myapplication3.repositories.PatientRepository;
import com.example.myapplication3.views.IPatientsView;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PatientByPhonePresenter {
    private IRepository repository;
    private IPatientsView view;
    public PatientByPhonePresenter(IPatientsView view){
        this.view=view;
        repository= PatientRepository.getRepository();
    }

    @RequiresApi(api= Build.VERSION_CODES.N)
    public void onStart(){
        ArrayList<Patient> patients = (ArrayList<Patient>)repository.getAll()
                .stream()
                .filter(p -> p.getPhone().startsWith("23"))
                .collect(Collectors.toList());
        view.showPatients(patients);
    }
}
