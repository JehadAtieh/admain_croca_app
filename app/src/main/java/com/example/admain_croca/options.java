package com.example.admain_croca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }

    public void new_accident(View view) {
        Intent intent = new Intent(options.this, details.class);
        startActivity(intent);
    }

    public void inquire(View view) {
        Intent intent = new Intent(options.this, Inquire.class);
        startActivity(intent);
    }

    public void view_Map(View view) {

    }

    public void information(View view) {
        Intent intent = new Intent(options.this, information.class);
        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(options.this, opstion2.class);
        startActivity(intent);
    }
}