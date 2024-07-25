package com.example.myapplication3.presenters;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.models.Patient;
import com.example.myapplication3.repositories.IRepository;
import com.example.myapplication3.repositories.PatientRepository;
import com.example.myapplication3.utils.ViewLauncher;
import com.example.myapplication3.views.IMainView;

public class MainPresenter implements IPresenter{

    private IMainView mainView;
    private IRepository mainRepository;
    private ViewLauncher launcher;
    private Context context;
    public MainPresenter(IMainView mainView){
        this.mainView=mainView;
        this.mainRepository= PatientRepository.getRepository();
        launcher=new ViewLauncher((AppCompatActivity)mainView);
    }
    @Override
    public void onStart() {

        mainView.showPatients(mainRepository.getAll());
    }
    @Override
    public void onDestroy(Patient patient) {
        mainRepository.delete(patient);
        mainView.showPatients(mainRepository.getAll());
    }

    @Override
    public void onEditPatientClicked(Patient patient) {
        mainRepository.delete(patient);
        mainView.showEditPatient(patient);
    }

    @Override
    public void onShowByDiagnosisClicked() {
        launcher.startPatientsByDiagnosisActivity();
    }

    @Override
    public void onShowByPhoneClicked() {
        launcher.startPatientsByPhoneActivity();
    }

    @Override
    public void onAddPatientClicked() {
        launcher.startAddPatientActivity();
    }
}
