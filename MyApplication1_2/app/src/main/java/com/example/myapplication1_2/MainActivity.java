package com.example.myapplication1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showMessage(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        String message = "Привет, " + buttonText + "!!!";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}