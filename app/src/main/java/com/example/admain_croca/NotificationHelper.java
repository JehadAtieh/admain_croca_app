package com.example.admain_croca;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

public class NotificationHelper {

    private static final String CHANNEL_ID = "canWriteChannel";
    private static final String TAG = "NotificationHelper";

    public NotificationHelper() {
    }

    public void showNotification(Context context, String title, String message) {

        Intent intent = new Intent(context, receive_data.class); // استخدم context بدلاً من this
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        if (checkNotificationPermission(context)) {
            createNotificationChannel(context);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.bell)  // تأكد من وجود هذا المورد
                    .setContentTitle(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent) // إضافة PendingIntent هنا
                    .setAutoCancel(true);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(1, builder.build());
        } else {
            Log.e(TAG, "Notification permission not granted.");
        }
    }

    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "CanWriteChannel";
            String description = "Channel for CanWrite notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            } else {
                Log.e(TAG, "NotificationManager is null, could not create notification channel.");
            }
        }
    }

    private boolean checkNotificationPermission(Context context) {
        int permissionCheck = ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS);
        return permissionCheck == android.content.pm.PackageManager.PERMISSION_GRANTED;
    }
}
