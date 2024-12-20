package com.example.bookstoreapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import com.example.bookstoreapp.R;


public class MainActivity extends AppCompatActivity {

    ListView bookListView;
    ArrayList<Book> bookList;
    BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        checkLoginStatus();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.getOverflowIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);


        bookListView = findViewById(R.id.booklistview);
        bookList = new ArrayList<>();


        bookList.add(new Book("MAHAL MO SIYA. MAHAL KA BA?", "Marcelo Santos III", 300.0, R.drawable.book1));
        bookList.add(new Book("MOVE ON. WALANG FOREVER!", "Marcelo Santos III", 458.9, R.drawable.book2));
        bookList.add(new Book("Para sa Broken Hearted", "Marcelo Santos III", 500.0, R.drawable.book3));


        bookAdapter = new BookAdapter(this, bookList);
        bookListView.setAdapter(bookAdapter);


        bookListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, BookDetailsActivity.class);
            intent.putExtra("book", bookList.get(position));
            startActivity(intent);
        });
    }


    private void checkLoginStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (!isLoggedIn) {

            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_cart) {

            Intent cartIntent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(cartIntent);
            return true;
        } else if (itemId == R.id.action_logout) {

            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void logout() {

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();


        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
