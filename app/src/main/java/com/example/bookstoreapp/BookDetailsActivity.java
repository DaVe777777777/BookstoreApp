package com.example.bookstoreapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BookDetailsActivity extends AppCompatActivity {

    TextView title, author, price;
    ImageView bookImage, backIcon;
    Button addToCartButton;
    Book selectedBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.getOverflowIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);


        backIcon = findViewById(R.id.backIcon);
        backIcon.setOnClickListener(v -> {

            Intent intent = new Intent(BookDetailsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });


        title = findViewById(R.id.bookTitleDetails);
        author = findViewById(R.id.bookAuthorDetails);
        price = findViewById(R.id.bookPriceDetails);
        bookImage = findViewById(R.id.bookImageDetails);
        addToCartButton = findViewById(R.id.addToCartButton);


        selectedBook = (Book) getIntent().getSerializableExtra("book");


        title.setText(selectedBook.getTitle());
        author.setText(selectedBook.getAuthor());
        price.setText(String.valueOf(selectedBook.getPrice()));
        bookImage.setImageResource(selectedBook.getImage());


        addToCartButton.setOnClickListener(v -> {
            CartManager.getInstance().addToCart(selectedBook);
            Toast.makeText(BookDetailsActivity.this, "Added to Cart!", Toast.LENGTH_SHORT).show();
        });
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

            Intent cartIntent = new Intent(BookDetailsActivity.this, CartActivity.class);
            startActivity(cartIntent);
            return true;
        } else if (itemId == R.id.action_logout) {

            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void logout() {

        getSharedPreferences("UserPrefs", MODE_PRIVATE).edit().clear().apply();


        Intent intent = new Intent(BookDetailsActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
