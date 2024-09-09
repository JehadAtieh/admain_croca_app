package com.example.admain_croca;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class driverInformation extends AppCompatActivity {

    private TextView tv_11, tv_12, tv_13, tv_14;
    private FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_information);

        // Initialize TextViews
        tv_11 = findViewById(R.id.tv_11);
        tv_12 = findViewById(R.id.tv_12);
        tv_13 = findViewById(R.id.tv_13);
        tv_14 = findViewById(R.id.tv_14);

        db = FirebaseFirestore.getInstance();

        // Get plate number from intent
        String plateNumber = getIntent().getStringExtra("Plate_Number");

        // Query Firestore
        db.collection("Name_Driver").document(plateNumber).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String Name = documentSnapshot.getString("الاسم الرباعي");
                            String Driver_nationality = documentSnapshot.getString("جنسية السائق");
                            String State = documentSnapshot.getString("حالة السائق");


                            // Display data in TextViews
                            tv_11.setText(Name);
                            tv_12.setText(plateNumber);
                            tv_13.setText(State);
                            tv_14.setText(Driver_nationality);

                        } else {
                            Toast.makeText(driverInformation.this, "No data found for this plate number", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(driverInformation.this, "Error retrieving data", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
