package com.example.admain_croca;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;

public class incident_representative extends AppCompatActivity {

    private FirebaseFirestore db;
    private TextView textViewSerialNumber, textViewAccidentNumber, textViewPedestrianControl, textViewTrafficControl, policeDirectorate,
            securityCenter, coordinates, textView17, textView18, textView19, textView20, textView22, textView23,
            textView25,textView16 , textView33, editTextText22, textView32, editTextText21, editTextText20, editTextText19;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident_representative);

        db = FirebaseFirestore.getInstance();

        initializeTextViews();

        String accidentNumber = getIntent().getStringExtra("numCar");
        if (accidentNumber != null) {
            fetchIncidentData(accidentNumber);
        }
    }

    private void initializeTextViews() {
        policeDirectorate = findViewById(R.id.Police_Directorate);
        securityCenter = findViewById(R.id.Security_Center);
        textViewSerialNumber = findViewById(R.id.textViewSerialNumber);
        textViewAccidentNumber = findViewById(R.id.textViewAccidentNumber);
        textViewPedestrianControl = findViewById(R.id.editTextText19);
        textViewTrafficControl = findViewById(R.id.editTextText20);
        coordinates = findViewById(R.id.Coordinates);
        textView17 = findViewById(R.id.textView17);
        textView16 = findViewById(R.id.textView16);
        textView18 = findViewById(R.id.textView18);
        textView19 = findViewById(R.id.textView19);
        textView20 = findViewById(R.id.textView20);
        textView22 = findViewById(R.id.textView22);
        textView23 = findViewById(R.id.textView23);
        textView25 = findViewById(R.id.textView25);
        textView33 = findViewById(R.id.textView33);
        editTextText22 = findViewById(R.id.editTextText22);
        textView32 = findViewById(R.id.textView32);
        editTextText21 = findViewById(R.id.editTextText21);
        editTextText20 = findViewById(R.id.editTextText20);
        editTextText19 = findViewById(R.id.editTextText19);
    }

    private void fetchIncidentData(String accidentNumber) {
        db.collection("incident_data").document(accidentNumber).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        populateTextViews(documentSnapshot);
                    } else {
                        Toast.makeText(incident_representative.this, "No data found for this accident number", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore", "Error fetching data", e);
                    Toast.makeText(incident_representative.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                });
    }

    private void populateTextViews(DocumentSnapshot documentSnapshot) {
        if (policeDirectorate != null) policeDirectorate.setText(documentSnapshot.getString("مديرية الشرطة"));
        if (securityCenter != null) securityCenter.setText(documentSnapshot.getString("مركز الامن"));
        if (textViewSerialNumber != null) textViewSerialNumber.setText(documentSnapshot.getString("الرقم التسلسلي"));
        if (textView16 != null) textView16.setText("نوع الحادث: " + documentSnapshot.getString("نوع الحادث"));
        if (textViewAccidentNumber != null) textViewAccidentNumber.setText(documentSnapshot.getString("رقم الحادث"));
        if (textViewPedestrianControl != null) textViewPedestrianControl.setText(documentSnapshot.getString("مراقبة المشاة"));
        if (textViewTrafficControl != null) textViewTrafficControl.setText(documentSnapshot.getString("التحكم بالمرور"));
        if (coordinates != null) coordinates.setText(documentSnapshot.getString("الإحداثيات"));
        if (textView17 != null) textView17.setText("نوع التصادم: " + documentSnapshot.getString("نوع التصادم"));
        if (textView18 != null) textView18.setText("نوع التصادم الثانوي: " + documentSnapshot.getString("نوع التصادم الثانوي"));
        if (textView19 != null) textView19.setText("شكل الحادث: " + documentSnapshot.getString("شكل الحادث"));
        if (textView20 != null) textView20.setText("عدد الأشخاص المصابين في الحادث: " + documentSnapshot.getString("عدد الاشخاص المصابين في الحادث"));
        if (textView22 != null) textView22.setText("اتجاه سير الطريق: " + documentSnapshot.getString("اتجاه سير الطريق"));
        if (textView23 != null) textView23.setText("عدد مسارب الطريق: " + documentSnapshot.getString("عدد مسارب الطريق"));
        if (textView25 != null) textView25.setText("نوع سطح الطريق: " + documentSnapshot.getString("نوع سطح الطريق"));
        if (textView33 != null) textView33.setText("حالة الطقس: " + documentSnapshot.getString("حالة الطقس"));
        if (editTextText22 != null) editTextText22.setText("خصائص الطريق: " + documentSnapshot.getString("خصائص الطريق"));
        if (textView32 != null) textView32.setText("الإضاءة: " + documentSnapshot.getString("إضاءة"));
        if (editTextText21 != null) editTextText21.setText("حدود السرعة: " + documentSnapshot.getString("حدود السرعه"));
        if (editTextText20 != null) editTextText20.setText("ضوابط حركة السير: " + documentSnapshot.getString("ضوابط حركة السير"));
        if (editTextText19 != null) editTextText19.setText("ضوابط حركة المشاة: " + documentSnapshot.getString("مراقبة المشاة"));
    }
}
