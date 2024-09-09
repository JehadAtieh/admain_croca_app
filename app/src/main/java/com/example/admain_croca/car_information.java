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

public class car_information extends AppCompatActivity {

    private TextView tv_11, tv_12, tv_13, tv_14, tv_15, tv_16, tv_17;
    private FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_information);

        // Initialize TextViews
        tv_11 = findViewById(R.id.tv_11);
        tv_12 = findViewById(R.id.tv_12);
        tv_13 = findViewById(R.id.tv_13);
        tv_14 = findViewById(R.id.tv_14);
        tv_15 = findViewById(R.id.tv_15);
        tv_16 = findViewById(R.id.tv_16);
        tv_17 = findViewById(R.id.tv_17);

        db = FirebaseFirestore.getInstance();

        // Get plate number from intent
        String plateNumber = getIntent().getStringExtra("Plate_Number");

        // Query Firestore
        db.collection("cars").document(plateNumber).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String nationality = documentSnapshot.getString("جنسية المركبة ");
                            String vehicleClass = documentSnapshot.getString("فئة المركبة");
                            String vehicleType = documentSnapshot.getString("نوع المركبة");
                            String vehicleStatus = documentSnapshot.getString("حالة المركبة");
                            String ownerHealth = documentSnapshot.getString("صحة المالك");
                            String ownerName = documentSnapshot.getString("اسم المالك");

                            // Display data in TextViews
                            tv_11.setText(plateNumber);
                            tv_12.setText(vehicleClass);
                            tv_13.setText(vehicleType);
                            tv_14.setText(nationality);
                            tv_15.setText(vehicleStatus);
                            tv_16.setText(ownerHealth);
                            tv_17.setText(ownerName);
                        } else {
                            Toast.makeText(car_information.this, "No data found for this plate number", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(car_information.this, "Error retrieving data", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
