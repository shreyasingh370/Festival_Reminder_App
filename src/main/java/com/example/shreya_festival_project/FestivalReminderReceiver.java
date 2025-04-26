package com.example.shreya_festival_project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class FestivalReminderReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String festivalName = intent.getStringExtra("festival_name");

        // Check for notification permission on Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                context.checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                        != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            // Exit if permission is not granted
            return;
        }

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "festival_channel")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Festival Reminder")
                .setContentText("Today is " + festivalName + "! 🎉")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true); // Dismiss when tapped

        // Send the notification
        int notificationId = (int) (System.currentTimeMillis() & 0xfffffff);
        NotificationManagerCompat.from(context).notify(notificationId, builder.build());
    }
}
