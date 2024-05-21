package com.example.tareasprofcaei;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import MenuLateral.menulateral;

public class splash_screen extends AppCompatActivity {
    public static int tiempoCarga = 3000;
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
}