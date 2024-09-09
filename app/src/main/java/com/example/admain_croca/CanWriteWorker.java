package com.example.admain_croca;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CanWriteWorker extends Worker {

    private static final String TAG = "CanWriteWorker";

    public CanWriteWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("meta_data").document("canWriteVar")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Boolean canWrite = document.getBoolean("canWrite");
                            if (canWrite != null && canWrite) {
                                sendNotification();
                            } else {
                                Log.i(TAG, "canWrite is false");
                            }
                        } else {
                            Log.i(TAG, "Document does not exist");
                        }
                    } else {
                        Log.e(TAG, "Error getting document: ", task.getException());
                    }
                });
        return Result.success();
    }

    private void sendNotification() {
        String notificationTitle = "تم استلام البيانات";
        String notificationMessage = "تم إرسال بيانات الحادث بنجاح";

        // Get current time
        Date currentTime = new Date();

        // Format the time
        DateFormat dateFormat = new SimpleDateFormat(" ", Locale.getDefault());
        String formattedTime = dateFormat.format(currentTime);

        // Add time to the notification message
        notificationMessage += formattedTime;

        NotificationHelper notificationHelper = new NotificationHelper();
        notificationHelper.showNotification(getApplicationContext(), notificationTitle, notificationMessage);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("meta_data").document("canWriteVar")
                .update("canWrite", false)
                .addOnSuccessListener(aVoid -> {
                    Log.i(TAG, "تم تحديث canWrite إلى false، يمكنك الآن متابعة العملية");
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Error updating canWrite value", e);
                });
    }
}
