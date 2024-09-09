package com.example.admain_croca;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    private EditText militaryNumberEditText, passwordEditText;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = FirebaseFirestore.getInstance();

        militaryNumberEditText = findViewById(R.id.editTextNumber5);
        passwordEditText = findViewById(R.id.editTextNumber2);
    }

    public void enter(View view) {
        String militaryNumber = militaryNumberEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        db.collection("users").document(militaryNumber).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String storedPassword = documentSnapshot.getString("password");
                if (storedPassword != null && storedPassword.equals(password)) {
                    Intent intent = new Intent(login.this, opstion2.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(login.this, "كلمة المرور غير صحيحة", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(login.this, "المستخدم غير موجود", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> {
            Toast.makeText(login.this, "خطأ في التحقق من البيانات", Toast.LENGTH_SHORT).show();
        });
    }
}
