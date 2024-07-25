package com.example.myapplication3.contracts;

import android.content.Context;
import android.content.Intent;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication3.views.PatientsByDiagnosisActivity;

public class PatientsByDiagnosisActivityContract extends ActivityResultContract {
    @NonNull
    @Override
    public Intent createIntent(@NonNull Context context, Object input){
        return new Intent(context, PatientsByDiagnosisActivity.class);
    }
    @Override
    public Object parseResult(int resultCode, @Nullable Intent intent){
        return null;
    }
}
