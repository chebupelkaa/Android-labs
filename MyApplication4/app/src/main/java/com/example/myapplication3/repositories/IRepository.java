package com.example.myapplication3.repositories;

import android.content.Context;

import com.example.myapplication3.models.Patient;
import java.util.ArrayList;
import java.util.List;

public interface IRepository {
    Patient get(String name);
    ArrayList<Patient> getAll();
    void add(Patient patient);
    void delete(Patient patient);

}
