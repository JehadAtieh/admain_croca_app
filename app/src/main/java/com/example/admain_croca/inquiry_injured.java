package com.example.admain_croca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class inquiry_injured extends AppCompatActivity {

    EditText editText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry_injured);
        editText=findViewById(R.id.editTextText19);

    }


    public void back(View view) {
        Intent intent=new Intent(inquiry_injured.this,Inquire.class);
        startActivity(intent);
    }

    public void search(View view) {
        // استرجاع النص المدخل من EditText
        String ID_injured = editText.getText().toString().trim();

        // التأكد من أن الحقل ليس فارغاً
        if (ID_injured.isEmpty()) {
            Toast.makeText(this, "الرجاء إدخال الرقم الوطني للمصاب", Toast.LENGTH_SHORT).show();
            return;
        }

        // تهيئة Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Infected")
                .document(ID_injured)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document != null && document.exists()) {
                                // العثور على الوثيقة
                                Intent intent = new Intent(inquiry_injured.this, driverInformation.class);
                                intent.putExtra("ID_injured", ID_injured);
                                startActivity(intent);
                            } else {
                                Toast.makeText(inquiry_injured.this, "لم يتم العثور على اسم المصاب", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // فشل الاستعلام
                            Log.w("Firestore", "خطأ في جلب الوثائق.", task.getException());
                        }
                    }
                });
    }
}
