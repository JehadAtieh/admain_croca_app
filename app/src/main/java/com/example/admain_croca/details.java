package com.example.admain_croca;// package com.example.admain_croca;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class details extends AppCompatActivity {

    private EditText editTextText19;
    private EditText editTextText20;
    private EditText editTextNumber4;
    private TextView editTextText9;
    private EditText editTextText10;
    private EditText editTextText21;
    private EditText editTextText22;
    private EditText editTextNumber6;
    private EditText editTextNumber7; // EditText للإحداثيات
    private Spinner spinner6;
    private Spinner spinner7;
    private Spinner spinner8;
    private Spinner spinner9;
    private Spinner spinner10;
    private Spinner spinner11;
    private Spinner spinner13;
    private Spinner spinner19;
    private Spinner spinner18;
    private Spinner spinner15; // Spinner لإدارة الشرطة
    private Spinner spinner16; // Spinner لمركز الأمن
    private Button button11;
    private FirebaseFirestore db;
    private Long lastAccidentNumber = 0L;
    private Map<String, List<String>> securityCentersMap;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // إعداد Firestore
        db = FirebaseFirestore.getInstance();

        // تهيئة جميع عناصر الواجهة


        editTextText19 = findViewById(R.id.editTextText19);
        editTextNumber4 = findViewById(R.id.editTextNumber4);
        editTextText21 = findViewById(R.id.editTextText21);
        editTextText20 = findViewById(R.id.editTextText20);
        editTextNumber4 = findViewById(R.id.editTextNumber4);
        editTextText9 = findViewById(R.id.num_accident); // تهيئة الـ EditText لعرض وتحرير رقم الحادث
        editTextText10 = findViewById(R.id.editTextText10);
        editTextText22 = findViewById(R.id.editTextText22);
        editTextNumber6 = findViewById(R.id.editTextNumber6);
        editTextNumber7 = findViewById(R.id.editTextNumber7); // تهيئة الـ EditText للإحداثيات
        spinner6 = findViewById(R.id.spinner6);
        spinner7 = findViewById(R.id.spinner7);
        spinner8 = findViewById(R.id.spinner8);
        spinner9 = findViewById(R.id.spinner9);
        spinner10 = findViewById(R.id.spinner10);
        spinner11 = findViewById(R.id.spinner11);
        spinner13 = findViewById(R.id.spinner13);
        spinner18 = findViewById(R.id.spinner18);
        spinner15 = findViewById(R.id.spinner15); // تهيئة الـ Spinner لإدارة الشرطة
        spinner19 = findViewById(R.id.spinner19);
        spinner16 = findViewById(R.id.spinner16); // تهيئة الـ Spinner لمركز الأمن
        button11 = findViewById(R.id.button11);

        // إعداد بيانات مراكز الأمن لكل مديرية
        setupSecurityCentersData();

        // إعداد OnItemSelectedListener لـ Spinner المديريات
        spinner15.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedDirectorate = parent.getItemAtPosition(position).toString();
                updateSecurityCentersSpinner(selectedDirectorate);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // يمكن تركه فارغاً
            }
        });

        // Load the last accident number from Firestore and increment it by 1
        loadLastAccidentNumber();

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // استرجاع البيانات من جميع عناصر الواجهة (الـ EditText والـ Spinners)
                String serialNumber = editTextText10.getText().toString();
                String num_car_forAcc = editTextNumber4.getText().toString();
                String num_accident = editTextText9.getText().toString();
                String pedestrianControl = editTextText19.getText().toString();
                String trafficControl = editTextText20.getText().toString();
                String roadSurfaceType = spinner7.getSelectedItem().toString();
                String weatherCondition1 = spinner19.getSelectedItem().toString();
                String secondaryAccidentType = spinner8.getSelectedItem().toString();
                String Number_of_lanes = spinner11.getSelectedItem().toString();
                String laneCount = editTextNumber6.getText().toString();
                String coordinates = editTextNumber7.getText().toString();
                String lighting = spinner18.getSelectedItem().toString();
                String policeDepartment = spinner15.getSelectedItem().toString();
                String typeLine = spinner13.getSelectedItem().toString();
                String direction_of_travel = spinner10.getSelectedItem().toString();
                String Incident_form = spinner9.getSelectedItem().toString();
                String securityCenter = spinner16.getSelectedItem().toString();
                String vehicleCount = spinner6.getSelectedItem().toString();
                String speedLimits = editTextText21.getText().toString();
                String Road_characteristics = editTextText22.getText().toString();
                String weatherCondition = spinner19.getSelectedItem().toString();


                // رفع البيانات إلى Firestore
                Map<String, String> incidentData = new HashMap<>();
                incidentData.put("الرقم التسلسلي", serialNumber);
                incidentData.put("نوع سطح الطريق", typeLine);
                incidentData.put("عدد مسارب الطريق", Number_of_lanes);
                incidentData.put("عدد الاشخاص المصابين في الحادث", laneCount);
                incidentData.put("شكل الحادث", Incident_form);
                incidentData.put("عدد المركبات المشتركه في الحادث", num_car_forAcc);
                incidentData.put("رقم الحادث", num_accident);
                incidentData.put("مراقبة المشاة", pedestrianControl);
                incidentData.put("نوع التصادم", roadSurfaceType);
                incidentData.put("نوع التصادم الثانوي", secondaryAccidentType);
                incidentData.put("خصائص الطريق", Road_characteristics);
                incidentData.put("الإحداثيات", coordinates);
                incidentData.put("نوع الحادث", vehicleCount);
                incidentData.put("إضاءة", lighting);
                incidentData.put("مديرية الشرطة", policeDepartment);
                incidentData.put("مركز الامن", securityCenter);
                incidentData.put("ضوابط حركة السير", trafficControl);
                incidentData.put("اتجاه سير الطريق", direction_of_travel);
                incidentData.put("حدود السرعه", speedLimits);
                incidentData.put("حالة الارض", weatherCondition);
                incidentData.put("حالة الطقس", weatherCondition);
                Toast.makeText(details.this, "تم اضافة الحادث بنجاح.", Toast.LENGTH_SHORT).show();

                            // زيادة قيمة num_accident بمقدار 1 في Firestore
                            long newAccidentNumber = lastAccidentNumber + 1; // تحديث القيمة المحلية للرقم
                            db.collection("num_details_accident").document("num_details_accident")
                                    .update("num_accident", newAccidentNumber)
                                    .addOnSuccessListener(aVoid -> {
                                        Log.d("Firestore", "تمت زيادة قيمة num_accident بنجاح");

                                        // إضافة الوثيقة باستخدام الرقم المحدث كاسم لها
                                        db.collection("incident_data").document(String.valueOf(newAccidentNumber))
                                                .set(incidentData)
                                                .addOnSuccessListener(aVoid1 -> {
                                                    Log.d("Firestore", "تمت إضافة الوثيقة بنجاح");
                                                })
                                                .addOnFailureListener(e -> {
                                                    Log.e("Firestore", "حدث خطأ أثناء إضافة الوثيقة", e);
                                                });
                                    })
                                    .addOnFailureListener(e -> {
                                        Log.e("Firestore", "حدث خطأ أثناء زيادة قيمة num_accident", e);
                                    });
            }
        });
    }

    private void loadLastAccidentNumber() {
        db.collection("num_details_accident").document("num_details_accident")
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Long lastAccidentNumber = documentSnapshot.getLong("num_accident");
                        if (lastAccidentNumber != null) {
                            this.lastAccidentNumber = lastAccidentNumber;
                            // Increment the last accident number by 1 and display it in the EditText
                            editTextText9.setText(String.valueOf(lastAccidentNumber + 1));
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore", "Error reading last accident number", e);
                    Log.e("Firestore", "Error reading last accident number", e);
                });
    }

    private void setupSecurityCentersData() {
        securityCentersMap = new HashMap<>();
        // هنا تقوم بإضافة جميع المديريات ومراكزها الأمنية
        securityCentersMap.put("مديرية شرطة الزرقاء", new ArrayList<String>() {{
            add("مركز أمن الرصيفة");
            add("مركز أمن الحي الشرقي");
            add("مركز أمن جويدة");
            add("مركز أمن طارق");
            add("مركز أمن الرشيد");
            add("مركز أمن ماركا");
        }});
        securityCentersMap.put("مديرية شرطة عمان", new ArrayList<String>() {{
            add("مركز أمن المدينة");
            add("مركز أمن الجبل");
            add("مركز أمن مرج الحمام");
            add("مركز أمن تلاع العلي");
            add("مركز أمن المقابلين");
            add("مركز أمن القويسمة");
            add("مركز أمن البيادر");
            add("مركز أمن الهاشمي");
            add("مركز أمن شفا بدران");
            add("مركز أمن صويلح");
            add("مركز أمن ابو نصير");
            add("مركز أمن شمال عمان");
        }});
        securityCentersMap.put("مديرية شرطة العقبة", new ArrayList<String>() {{
            add("مركز أمن العقبة الشمالي");
            add("مركز أمن العقبة الجنوبي");
        }});
    }

    private void updateSecurityCentersSpinner(String selectedDirectorate) {
        List<String> securityCenters = securityCentersMap.get(selectedDirectorate);
        if (securityCenters != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, securityCenters);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner16.setAdapter(adapter);
        }
    }
}
