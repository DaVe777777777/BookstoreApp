package com.example.bookstoreapp;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;
    private double price;
    private int image;

    public Book(String title, String author, double price, int image) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }
}
