package com.example.myapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication3.models.Patient;
import com.example.myapplication3.presenters.IPresenter;
import com.example.myapplication3.presenters.MainPresenter;
import com.example.myapplication3.views.AddPatientActivity;
import com.example.myapplication3.views.IMainView;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView {
    private IPresenter mPresenter;
    private ListView patientList;
    private Patient selectedPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        patientList=(ListView) findViewById(R.id.patientListView);
        patientList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPatient = (Patient) patientList.getItemAtPosition(position);
            }
        });

        mPresenter=new MainPresenter(this);
        mPresenter.onStart();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.onStart();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean  onOptionsItemSelected(@NonNull MenuItem item){
        int id=item.getItemId();
        if (id == R.id.coronasItem) {
            mPresenter.onShowByDiagnosisClicked();
            return true;
        } else if (id == R.id.addItem) {
            mPresenter.onAddPatientClicked();
            return true;
        } else if (id == R.id.deleteItem) {
            mPresenter.onDestroy(selectedPatient);
            return true;
        } else if (id == R.id.phonesItem) {
            mPresenter.onShowByPhoneClicked();
            return true;
        } else if (id == R.id.editItem) {
            mPresenter.onEditPatientClicked(selectedPatient);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showPatients(List<Patient> patients) {
        ArrayAdapter<Patient> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,patients);
        patientList.setAdapter(adapter);

    }

    @Override
    public void addPatient() {

    }

    @Override
    public void deletePatients() {

    }

    @Override
    public void showEditPatient(Patient patient) {
        Intent intent = new Intent(this, AddPatientActivity.class);
        intent.putExtra("patientToEdit", patient);
        startActivity(intent);
    }

    @Override
    public void showByDiagnosis() {

    }

    @Override
    public void showByPhone() {

    }
}