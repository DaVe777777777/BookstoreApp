package com.example.bookstoreapp;

import java.util.ArrayList;

public class CartManager {

    private static CartManager instance;
    private ArrayList<Book> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addToCart(Book book) {
        cartItems.add(book);
    }

    public void clearCart() {
        cartItems.clear();
    }

    public ArrayList<Book> getCartItems() {
        return cartItems;
    }

    public double calculateTotal() {
        double total = 0;
        for (Book book : cartItems) {
            total += book.getPrice();
        }
        return total;
    }
}
