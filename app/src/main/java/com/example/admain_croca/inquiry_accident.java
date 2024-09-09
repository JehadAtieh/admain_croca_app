package com.example.admain_croca;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class inquiry_accident extends AppCompatActivity {

    EditText editText;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry_accident);

        editText=findViewById(R.id.num_car);


    }

    public void back(View view) {
        Intent intent = new Intent(inquiry_accident.this, Inquire.class);
        startActivity(intent);
    }


    public void search(View view) {
        String numCar = editText.getText().toString();
        Intent intent = new Intent(inquiry_accident.this, incident_representative.class);
        intent.putExtra("numCar", numCar);
        startActivity(intent);
    }
}
