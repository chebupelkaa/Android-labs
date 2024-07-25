package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookActivity2 extends AppCompatActivity {

    private static List<Book> bookList=new ArrayList<Book>();
    static {
        bookList.add(new Book("fhdjd","Толстой",2001,100));
        bookList.add(new Book("cms","Пушкин",2003,110));
        bookList.add(new Book("cms","Пушкин",2003,110));
        bookList.add(new Book("df","Пушкин",2002,110));
    }
    private List<Book> selectedBooks = new ArrayList<>();
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity2);

        ListView bookListView=(ListView) findViewById((R.id.booksList));
        ArrayAdapter<Book> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice,bookList);
        bookListView.setAdapter(adapter);
        bookListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        spinner = findViewById(R.id.authorSpinner);
        String[] authorsArray = getResources().getStringArray(R.array.authors);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, authorsArray);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedAuthor = (String) parentView.getItemAtPosition(position);

                if ("Все авторы".equals(selectedAuthor)) {
                    ArrayAdapter<Book> allBooksAdapter = new ArrayAdapter<>(BookActivity2.this, android.R.layout.simple_list_item_single_choice, bookList);
                    bookListView.setAdapter(allBooksAdapter);
                } else {

                    List<Book> filteredBooks = filterBooksByAuthor(selectedAuthor);
                    ArrayAdapter<Book> filteredAdapter = new ArrayAdapter<>(BookActivity2.this, android.R.layout.simple_list_item_single_choice, filteredBooks);
                    bookListView.setAdapter(filteredAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Book selectedBook = bookList.get(position);
                if (selectedBooks.contains(selectedBook)) {
                    selectedBooks.remove(selectedBook);
                } else {
                    selectedBooks.add(selectedBook);
                }
            }
        });

    }
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("selectedBooks", (Serializable) selectedBooks);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
    private List<Book> filterBooksByAuthor(String author) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getAuthor().equals(author)) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }
}
