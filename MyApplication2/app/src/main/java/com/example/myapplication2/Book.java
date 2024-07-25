package com.example.myapplication2;

import java.io.Serializable;

public class Book implements Serializable {
    private String name;
    private String author;
    private int yearOfPublish;
    private int pages;
    public Book(String name,String author,int yearOfPublish,int pages){
        this.author=author;
        this.name=name;
        this.pages=pages;
        this.yearOfPublish=yearOfPublish;
    }

    @Override
    public String toString(){
        return "Книга:"+name+
                " автор:"+author +
                " год издания:"+ yearOfPublish+
                " кол-во страниц:"+pages;
    }

    public String getAuthor() {
        return author;
    }
}
