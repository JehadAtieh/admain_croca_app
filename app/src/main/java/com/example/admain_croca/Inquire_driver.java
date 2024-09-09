package com.example.admain_croca;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Inquire_driver extends AppCompatActivity {

    EditText editTextPlateNumber;
    Button buttonSearch;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquire_driver);

        editTextPlateNumber = findViewById(R.id.num_car);
        buttonSearch = findViewById(R.id.btn_search);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
    }

    public void search() {
        // استرجاع النص المدخل من EditText
        String plateNumber = editTextPlateNumber.getText().toString().trim();

        // التأكد من أن الحقل ليس فارغاً
        if (plateNumber.isEmpty()) {
            Toast.makeText(this, "الرجاء إدخال رقم اللوحة", Toast.LENGTH_SHORT).show();
            return;
        }

        // تهيئة Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // البحث في مجموعة Firestore بناءً على رقم اللوحة المدخل
        db.collection("Name_Driver")
                .document(plateNumber) // استخدام رقم اللوحة كمعرف للوثيقة
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document != null && document.exists()) {
                                // العثور على الوثيقة
                                Intent intent = new Intent(Inquire_driver.this, driverInformation.class);
                                intent.putExtra("Plate_Number", plateNumber);
                                startActivity(intent);
                            } else {
                                // لم يتم العثور على وثيقة برقم اللوحة المدخل
                                Toast.makeText(Inquire_driver.this, "لم يتم العثور على اسم السائق", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // فشل الاستعلام
                            Log.w("Firestore", "خطأ في جلب الوثائق.", task.getException());
                        }
                    }
                });
    }
}
