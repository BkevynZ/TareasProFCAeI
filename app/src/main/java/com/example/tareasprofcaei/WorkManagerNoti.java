package com.example.tareasprofcaei;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.ProgressUpdater;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import MenuLateral.ui.perfil2.perfil2;

public class WorkManagerNoti extends Worker {
    public WorkManagerNoti(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }
public static void GuardarNoti(long duracion, Data data, String tag){
    OneTimeWorkRequest noti = new OneTimeWorkRequest.Builder(WorkManagerNoti.class)
            .setInitialDelay(duracion, TimeUnit.MILLISECONDS).addTag(tag)
            .setInputData(data).build();
    WorkManager instance = WorkManager.getInstance();
    instance.enqueue(noti);

}
    @NonNull
    @Override
    public Result doWork() {
String titulo= getInputData().getString("titulo");
        String detalle= getInputData().getString("detalle");
        int id = (int) getInputData().getLong("idnoti",0);

        Log.d("WorkManagerNoti", "Titulo: " + titulo);
        Log.d("WorkManagerNoti", "Detalle: " + detalle);
        Log.d("WorkManagerNoti", "ID: " + id);

        oreo(titulo, detalle);
        return Result.success();
    }

    private void oreo(String t, String d){
        String id ="message";
        NotificationManager nm = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),id);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(id, "nuevo", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("notificaciopcasjkl");
            channel.setShowBadge(true);
            assert  nm != null;
            nm.createNotificationChannel(channel);

        }
        Intent i= new Intent(getApplicationContext(), perfil2.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent= PendingIntent.getActivity(getApplicationContext(),0 , i, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE );

        builder.setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(t)
                .setTicker("Nueva notifi")
                .setSmallIcon(R.drawable.iconcambiarperfil)
                .setContentText(d)
                .setContentIntent(pendingIntent)
                .setContentInfo("nuevo");
        Random random =new Random();
        int idNotify = random.nextInt(8000);

        assert nm != null;
        nm.notify(idNotify,builder.build());
    }
}
