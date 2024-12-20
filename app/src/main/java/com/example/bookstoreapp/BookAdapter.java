package com.example.bookstoreapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {

    Context context;
    ArrayList<Book> bookList;
    LayoutInflater inflater;

    public BookAdapter(Context context, ArrayList<Book> bookList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.book_item, parent, false);
        }


        TextView title = convertView.findViewById(R.id.bookTitle);
        TextView author = convertView.findViewById(R.id.bookAuthor);
        TextView price = convertView.findViewById(R.id.bookPrice);
        ImageView image = convertView.findViewById(R.id.bookImage);


        Book book = bookList.get(position);


        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        price.setText(String.valueOf(book.getPrice()));
        image.setImageResource(book.getImage());

        return convertView;
    }
}
