package com.example.tareasprofcaei;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

import MenuLateral.menulateral;

public class splash_screen extends AppCompatActivity {
    public static int tiempoCarga = 2500;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_FIRST_RUN = "firstRun";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });


// Programar la notificación aquí
        Log.e("sendNotification", "Antes de llamar al método scheduleNotification()");
        scheduleNotifications();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean firstRun = settings.getBoolean(KEY_FIRST_RUN, true);
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);

        String nombre = sharedPreferences.getString("Nombre", null);
        String imageUriString = sharedPreferences.getString("imageUri", null);

        new Handler().postDelayed(() -> {
            Intent intent;
            if (firstRun) {
                // Primera vez que se ejecuta la aplicación
                intent = new Intent(splash_screen.this, Caracteristica1.class);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean(KEY_FIRST_RUN, false);
                editor.apply();
            } else if (nombre != null && imageUriString != null) {
                // Datos de usuario ya guardados, redirigir a menulateral
                intent = new Intent(splash_screen.this, menulateral.class);
                intent.putExtra("Nombre", nombre);
                intent.putExtra("imageUri", imageUriString);
            } else {
                // No hay datos guardados, redirigir a nombreUsuario
                intent = new Intent(splash_screen.this, nombreUsuario.class);
            }
            startActivity(intent);
            finish();
        }, tiempoCarga);

    }

    private void scheduleNotifications() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String nombre = sharedPreferences.getString("Nombre", "Usuario"); // Valor por defecto "Usuario" si no se encuentra el nombre
        scheduleNotification(11, 34, 0, 1, "¡Hola "+nombre +"!","¿Ya acabaste tus materias?","Deja de pensarlas y anotalas en TareasProFCAeI ");
        scheduleNotification(11, 36,0, 2, "¡Hola "+nombre +"!","¿Ya acabaste tus materias?","Deja de pensarlas y anotalas en TareasProFCAeI ");
        scheduleNotification(11, 38,0, 3, "¡Oye "+nombre +"!","Ya pronto acaba el dia","¿Tienes tareas pendientes?, ¡Revisalo en TareasProFCAeI ");

    }

    private void scheduleNotification(int hora, int minutos, int segundos, int requestCode, String titulo,String mensaje1, String mensaje2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hora);
        calendar.set(Calendar.MINUTE, minutos);
        calendar.set(Calendar.SECOND, segundos);

        long currentTimeInMillis = System.currentTimeMillis();
        long notificationTimeInMillis = calendar.getTimeInMillis();

        if (notificationTimeInMillis <= currentTimeInMillis) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            notificationTimeInMillis = calendar.getTimeInMillis();
        }

        long delayInMillis = notificationTimeInMillis - currentTimeInMillis;
        Log.e("sendNotification", "Tiempo de retardo para la notificación: " + delayInMillis);

        NotificationUtils.scheduleNotification(this, notificationTimeInMillis, requestCode,   titulo, mensaje1,  mensaje2);
    }
}
