package com.example.myapplication2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button chooseActivityButton;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioGroup radioGroup;
    private EditText text;
    private static final int REQUEST_CODE_BOOK_ACTIVITY = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioButton1 = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        chooseActivityButton = findViewById(R.id.searchButton1);
        radioGroup=findViewById((R.id.radioGroup));
        text = findViewById(R.id.editTextText);

        Book selectedBook = (Book) getIntent().getSerializableExtra("selectedBook");
        if (selectedBook != null) {
            text.setText(selectedBook.toString());
        }

        chooseActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton1.setVisibility(View.VISIBLE);
                radioButton2.setVisibility(View.VISIBLE);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.radioButton) {
                    Intent intent1 = new Intent(MainActivity.this, BookActivity1.class);
                    startActivity(intent1);
                } else if (checkedId == R.id.radioButton2) {
                    Intent intent = new Intent(MainActivity.this, BookActivity2.class);
                    startActivityForResult(intent, REQUEST_CODE_BOOK_ACTIVITY);
                    //bookActivityLauncher.launch(intent);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_BOOK_ACTIVITY && resultCode == RESULT_OK) {
            List<Book> selectedBooks = (List<Book>) data.getSerializableExtra("selectedBooks");

            StringBuilder bookNames = new StringBuilder();
            for (Book book : selectedBooks) {
                bookNames.append(book.toString()).append("\n");
            }

            text.setText(bookNames.toString());

        }
    }

    private void setRadioButtonClickListeners(){
        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, BookActivity1.class);
                startActivity(intent1);
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BookActivity2.class);
                startActivityForResult(intent, REQUEST_CODE_BOOK_ACTIVITY);
            }
        });
    }

//    private ActivityResultLauncher<Intent> bookActivityLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            result -> {
//                if (result.getResultCode() == RESULT_OK) {
//                    Intent data = result.getData();
//                    if (data != null) {
//                        // Обработка данных из BookActivity2
//                        List<Book> selectedBooks = (List<Book>) data.getSerializableExtra("selectedBooks");
//                        text.setText(selectedBooks.toString());
//                    }
//                }
//            }
//    );

}