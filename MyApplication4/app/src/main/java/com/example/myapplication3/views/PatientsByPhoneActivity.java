package com.example.myapplication3.views;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;
import com.example.myapplication3.models.Patient;
import com.example.myapplication3.presenters.PatientByPhonePresenter;

import java.util.List;

public class PatientsByPhoneActivity extends AppCompatActivity implements IPatientsView{

    private PatientByPhonePresenter presenter;
    @RequiresApi(api= Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_patients);
        presenter=new PatientByPhonePresenter(this);
        presenter.onStart();
    }

    @Override
    public void showPatients(List<Patient> patients) {
        GridView patientsList=(GridView) findViewById(R.id.gridViewPatients);
        ArrayAdapter<Patient> adapter = new ArrayAdapter<Patient>(this, android.R.layout.simple_list_item_1,patients);
        patientsList.setAdapter(adapter);
    }

}
