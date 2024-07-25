package com.example.myapplication3.models;

import java.io.Serializable;

public class Patient implements Serializable {
    private int id;
    private String surname;
    private String phone;
    private String diagnosis;

    public Patient(String surname,String phone,String diagnosis){
        this.surname=surname;
        this.phone=phone;
        this.diagnosis=diagnosis;
    }
    public String getSurname(){ return surname; }
    public String getPhone(){return phone;}
    public String getDiagnosis(){return diagnosis;}

    @Override
    public String toString() {
        return "Фамилия: " + surname + "\nТелефон: "+phone+"\nДиагноз: " + diagnosis;
    }
}
