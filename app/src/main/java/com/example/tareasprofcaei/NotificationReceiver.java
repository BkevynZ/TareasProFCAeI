package com.example.tareasprofcaei;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import MenuLateral.menulateral;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("sendNotification", "Recibido el broadcast de la notificación");

        SharedPreferences sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String nombre = sharedPreferences.getString("Nombre", null);
        String imageUriString = sharedPreferences.getString("imageUri", null);

        Intent notificationIntent = new Intent(context, menulateral.class);
        notificationIntent.putExtra("Nombre", nombre);
        notificationIntent.putExtra("imageUri", imageUriString);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(NotificationUtils.CHANNEL_ID, "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
            Log.e("sendNotification", "Canal de notificación creado");
        }

        Bitmap imgNoti = BitmapFactory.decodeResource(context.getResources(), R.drawable.imnotificacion);
        String title = intent.getStringExtra("title");
        String mensaje1 = intent.getStringExtra("message1");
        String mensaje2 = intent.getStringExtra("message2");


        @SuppressLint("ResourceAsColor") Notification notification = new NotificationCompat.Builder(context, NotificationUtils.CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(mensaje1)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(mensaje1 + "\n" + mensaje2))
                .setColor(R.color.orange)
                .setSmallIcon(R.drawable.ic_notificacion)
                .setLargeIcon(imgNoti)
                .setContentIntent(pendingIntent)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(new long[]{0, 500, 1000})
                .setAutoCancel(true)
                .build();

        Log.e("sendNotification", "Notificación construida");

        notificationManager.notify(NotificationUtils.NOTIFICATION_ID, notification);
        Log.e("sendNotification", "Notificación mostrada");
    }
}



