package com.example.myapplication3.presenters;

import com.example.myapplication3.models.Patient;
import com.example.myapplication3.repositories.IRepository;
import com.example.myapplication3.repositories.PatientRepository;
import com.example.myapplication3.views.IAddPatientView;

public class AddPatientPresenter {
    private IAddPatientView view;
    private IRepository repository;

    public AddPatientPresenter(IAddPatientView view){
        this.view=view;
        repository= PatientRepository.getRepository();
    }
    public void addPatient(){
        Patient patient=view.getData();
        repository.add(patient);
    }


}
