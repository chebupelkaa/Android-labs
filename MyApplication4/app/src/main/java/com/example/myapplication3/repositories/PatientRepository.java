package com.example.myapplication3.repositories;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.myapplication3.models.Patient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PatientRepository implements IRepository{
    private ArrayList<Patient> patients = new ArrayList<Patient>();
    private static IRepository repository;

    private PatientRepository(){
        loadDataFromFile();
        //patients.add(new Patient("Гринь","375298753855","грипп1"));
        //patients.add(new Patient("Самовендюк","375298753555","грипп2"));
        //patients.add(new Patient("Глушенок","375298754575","коронавирус"));
        //patients.add(new Patient("Мармеладова","235294454575","коронавирус"));
    }

    public static IRepository getRepository(){
        if (repository==null) repository=new PatientRepository();
        return repository;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    public Patient get(String name) {
        for (Patient patient : patients) {
            if (patient.getSurname().equals(name)) {
                return patient;
            }
        }
        return null;
    }
    @Override
    public ArrayList<Patient> getAll() {
        return patients;
    }
    @Override
    public void add(Patient patient) { patients.add(patient);}
    @Override
    public void delete(Patient patient) { patients.remove(patient); }


    private void loadDataFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                // Разделение строки на отдельные значения
                String[] values = line.split(",");

                // Создание объекта Patient на основе считанных значений
                String surname = values[0];
                String phone = values[1];
                String diagnosis = values[2];

                patients.add(new Patient(surname, phone, diagnosis));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
