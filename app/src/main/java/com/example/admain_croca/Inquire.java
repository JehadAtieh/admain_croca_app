package com.example.admain_croca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inquire extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquire);
    }

    public void in_accident(View view) {
        Intent intent=new Intent(Inquire.this,inquiry_accident.class);
        startActivity(intent);
    }

    public void in_car(View view) {
        Intent intent=new Intent(Inquire.this,inquiry_car.class);
        startActivity(intent);
    }

    public void in_injured(View view) {
        Intent intent=new Intent(Inquire.this,inquiry_injured.class);
        startActivity(intent);
    }

    public void in_street(View view) {
        Intent intent=new Intent(Inquire.this,inquiry_street.class);
        startActivity(intent);
    }

    public void back(View view) {
        Intent intent=new Intent(Inquire.this,options.class);
        startActivity(intent);
    }

    public void in_driver(View view) {
        Intent intent=new Intent(Inquire.this,Inquire_driver.class);
        startActivity(intent);
    }
}