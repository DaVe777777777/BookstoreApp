package com.example.bookstoreapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Book> cartItems;
    private LayoutInflater inflater;

    public CartAdapter(Context context, ArrayList<Book> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.cart_item, parent, false);
        }

        TextView title = convertView.findViewById(R.id.bookTitle);
        TextView price = convertView.findViewById(R.id.bookPrice);
        ImageView image = convertView.findViewById(R.id.bookImage);

        Book book = cartItems.get(position);

        title.setText(book.getTitle());
        price.setText(String.format("$%.2f", book.getPrice()));
        image.setImageResource(book.getImage());

        return convertView;
    }


    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}

