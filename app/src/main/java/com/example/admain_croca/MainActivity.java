package com.example.admain_croca;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.google.firebase.FirebaseApp;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_TIMEOUT = 3000; // 3 ثواني
    private static final int REQUEST_NOTIFICATION_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase
        try {
            FirebaseApp.initializeApp(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Request notification permission
        requestNotificationPermission();

        // Schedule the worker
        scheduleCanWriteWorker();

        // استخدم مؤقت لتأخير فتح الصفحة الرئيسية
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // انتقل إلى الصفحة الرئيسية بعد انتهاء المؤقت
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
                finish(); // انهاء نشاط صفحة الشعار بعد الانتقال إلى الصفحة الرئيسية
            }
        }, SPLASH_TIMEOUT);
    }

    private void requestNotificationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_NOTIFICATION_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_NOTIFICATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Notification permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void scheduleCanWriteWorker() {
        PeriodicWorkRequest canWriteWorkRequest =
                new PeriodicWorkRequest.Builder(CanWriteWorker.class, 1, TimeUnit.SECONDS)
                        .build();
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                "CanWriteWorker",
                ExistingPeriodicWorkPolicy.REPLACE,
                canWriteWorkRequest
        );
    }
}
