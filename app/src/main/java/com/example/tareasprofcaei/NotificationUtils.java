package com.example.tareasprofcaei;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;

import MenuLateral.menulateral;

public class NotificationUtils {
    public static final String CHANNEL_ID = "default_channel_id";
    public static final int NOTIFICATION_ID = 1;

    public static void scheduleNotification(Context context, long triggerAtMillis, int requestCode,  String titulo,String mensaje1, String mensaje2) {
        Intent notificationIntent = new Intent(context, NotificationReceiver.class);
        notificationIntent.putExtra("title", titulo);
        notificationIntent.putExtra("message1", mensaje1);
        notificationIntent.putExtra("message2", mensaje2);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

       // long futureInMillis = SystemClock.elapsedRealtime() + delayInMillis;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Channel description");

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }

        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);
    }


    public static PendingIntent getPendingIntent(Context context) {
        Intent intent = new Intent(context, menulateral.class); // Cambia esto a la actividad que quieras abrir al hacer clic en la notificación
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // Agrega el flag adecuado al crear el PendingIntent
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
    }
}
