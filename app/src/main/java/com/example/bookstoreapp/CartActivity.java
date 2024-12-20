package com.example.bookstoreapp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {

    ListView cartListView;
    TextView totalAmount;
    Button checkoutButton;
    ImageView backIcon;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        cartListView = findViewById(R.id.cartListView);
        totalAmount = findViewById(R.id.totalAmount);
        checkoutButton = findViewById(R.id.checkoutButton);
        backIcon = findViewById(R.id.backIcon);


        cartAdapter = new CartAdapter(this, CartManager.getInstance().getCartItems());
        cartListView.setAdapter(cartAdapter);


        updateTotalAmount();


        backIcon.setOnClickListener(v -> finish());


        checkoutButton.setOnClickListener(v -> {
            if (CartManager.getInstance().getCartItems().isEmpty()) {

                Toast.makeText(CartActivity.this, "No books in the cart.", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(CartActivity.this, "Checkout Successful!", Toast.LENGTH_SHORT).show();
                CartManager.getInstance().clearCart();
                cartAdapter.notifyDataSetChanged();
                updateTotalAmount();
            }
        });
    }

    private void updateTotalAmount() {
        double total = CartManager.getInstance().calculateTotal();
        totalAmount.setText("Total: $" + total);
    }
}
