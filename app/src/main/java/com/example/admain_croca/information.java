package com.example.admain_croca;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class information extends AppCompatActivity {

    TextView tv1, tv2;
    EditText nationalIdInput,name,num;
    FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        tv1 = findViewById(R.id.Viewdate1);
        tv2 = findViewById(R.id.Viewdate2);
        name=findViewById(R.id.editTextText13);
        num=findViewById(R.id.editTextText12);

        nationalIdInput = findViewById(R.id.nationalIdInput); // Assuming you have this input field

        db = FirebaseFirestore.getInstance();
    }

    public void enter(View view) {
        showDatePickerDialog(tv1);
    }

    public void date(View view) {
        showDatePickerDialog(tv2);
    }

    private void showDatePickerDialog(final TextView textView) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                String selectedDate = sdf.format(calendar.getTime());

                if (textView == tv2 && !isDateAfter(selectedDate, tv1.getText().toString())) {

                    Toast.makeText(getApplicationContext(), "التاريخ غير صالح", Toast.LENGTH_SHORT).show();
                } else {
                    textView.setText(selectedDate);
                }
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }


    private boolean isDateAfter(String selectedDate, String otherDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            Date date1 = sdf.parse(selectedDate);
            Date date2 = sdf.parse(otherDate);


            return date1.after(date2);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void send(View view) {
        String nationalId = nationalIdInput.getText().toString().trim();
        String date1 = tv1.getText().toString().trim();
        String date2 = tv2.getText().toString().trim();
        String FullName = name.getText().toString().trim();
        String numberLogin = num.getText().toString().trim();

        if (nationalId.isEmpty() || date1.isEmpty() || date2.isEmpty()) {
            Toast.makeText(this, "يرجى ملء جميع الحقول", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a map of the data to save
        Map<String, Object> reportData = new HashMap<>();
        reportData.put("من تاريخ", date1);
        reportData.put("الى تاريخ", date2);
        reportData.put("الاسم الرباعي", FullName);
        reportData.put("رقم التسجيل", numberLogin);


        db.collection("Rebort").document(nationalId)
                .set(reportData)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(information.this, "تم إرسال التقرير بنجاح", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(information.this, "فشل في إرسال التقرير", Toast.LENGTH_SHORT).show();
                });
    }
}
