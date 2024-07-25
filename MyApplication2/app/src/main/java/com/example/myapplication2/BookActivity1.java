package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class BookActivity1 extends AppCompatActivity {
    private static List<Book> books=new ArrayList<Book>();
    static {
        books.add(new Book("dfd","Пушкин",2001,100));
        books.add(new Book("dfd","Толстой",2003,110));
        books.add(new Book("ра","Толстой",2003,110));
        books.add(new Book("прр","Толстой",2003,110));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity1);

        GridView bookList=(GridView) findViewById((R.id.gridBook1));
        ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1,books);
        bookList.setAdapter(adapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                Book selectedBook = books.get(position);
                Intent intent=new Intent(BookActivity1.this,MainActivity.class);
                intent.putExtra("selectedBook", selectedBook);
                startActivity(intent);
            }
        };
        bookList.setOnItemClickListener(itemListener);

    }
}