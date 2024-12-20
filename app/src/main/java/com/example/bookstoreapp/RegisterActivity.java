package com.example.bookstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText name, email, password, confirmPassword;
    Button registerButton, loginButton;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        registerButton = findViewById(R.id.btn_register);
        loginButton = findViewById(R.id.btn_login);

        db = new DatabaseHelper(this);

        registerButton.setOnClickListener(v -> {

            String userName = name.getText().toString().trim();
            String userEmail = email.getText().toString().trim();
            String userPass = password.getText().toString().trim();
            String userConfirmPass = confirmPassword.getText().toString().trim();


            if (userName.isEmpty() || userEmail.isEmpty() || userPass.isEmpty() || userConfirmPass.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "All fields must be filled!", Toast.LENGTH_SHORT).show();
                return;
            }


            if (!userEmail.endsWith("@gmail.com")) {
                Toast.makeText(RegisterActivity.this, "Please enter a valid Gmail address!", Toast.LENGTH_SHORT).show();
                return;
            }


            if (!userPass.equals(userConfirmPass)) {
                Toast.makeText(RegisterActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                return;
            }


            if (db.insertUser(userName, userEmail, userPass)) {
                Toast.makeText(RegisterActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                finish();  // Close the activity after successful registration
            } else {
                Toast.makeText(RegisterActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
            }
        });


        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}
