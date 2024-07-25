package com.example.myapplication3.views;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;
import com.example.myapplication3.models.Patient;
import com.example.myapplication3.presenters.AddPatientPresenter;
import com.example.myapplication3.presenters.PatientsByDiagnosisPresenter;

public class AddPatientActivity  extends AppCompatActivity implements IAddPatientView{
    private AddPatientPresenter presenter;
    private Patient patientToEdit;
    private EditText surnameEditText;
    private EditText diagnosisEditText;
    private EditText phoneEditText;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_add_patient);
        presenter=new AddPatientPresenter(this);
        surnameEditText=(EditText) findViewById(R.id.editTextSurname);
        diagnosisEditText=(EditText) findViewById(R.id.editTextDiagnos);
        phoneEditText=(EditText) findViewById(R.id.editTextPhone);

        patientToEdit = (Patient) getIntent().getSerializableExtra("patientToEdit");
        if (patientToEdit != null) {
            surnameEditText.setText(patientToEdit.getSurname());
            diagnosisEditText.setText(patientToEdit.getDiagnosis());
            phoneEditText.setText(patientToEdit.getPhone());
        }
    }
    @Override
    public Patient getData(){
        return new Patient(surnameEditText.getText().toString(),
                diagnosisEditText.getText().toString(),
                phoneEditText.getText().toString());
    }
    public  void SavePatient(View view){
        presenter.addPatient();
        this.onBackPressed();
    }

}
