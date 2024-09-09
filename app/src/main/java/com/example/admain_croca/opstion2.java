package com.example.admain_croca;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class opstion2 extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opstion2);

        constraintLayout = findViewById(R.id.constraintLayout2);

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(opstion2.this, options.class);
                startActivity(intent);
            }
        });
    }

    public void enter(View view) {
        showPasswordDialog();
    }

    private void showPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_password, null);
        builder.setView(dialogView);

        final EditText passwordInput = dialogView.findViewById(R.id.passwordInput);

        builder.setTitle("ادخل كلمة السر")
                .setPositiveButton("دخول", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String enteredPassword = passwordInput.getText().toString();
                        checkPassword(enteredPassword);
                    }
                })
                .setNegativeButton("الغاء", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void checkPassword(String enteredPassword) {
        // Replace "your_collection" with your actual collection name
        db.collection("users")
                .whereEqualTo("password", enteredPassword)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (!querySnapshot.isEmpty()) {
                            // Password is correct, navigate to the new activity
                            Intent intent = new Intent(opstion2.this, options.class);
                            startActivity(intent);
                        } else {
                            // Password is incorrect, show a toast message
                            Toast.makeText(opstion2.this, "كلممة السر غير صحيحه", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Handle errors here
                        Toast.makeText(opstion2.this, "خطأ في الوصول الى كلمة السر", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
