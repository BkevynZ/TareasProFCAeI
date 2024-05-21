package com.example.tareasprofcaei;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Caracteristica3 extends AppCompatActivity {
Button boton;

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_FIRST_RUN = "firstRun";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_caracteristica3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ConstraintLayout layout = findViewById(R.id.main);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a Caracteristica1 si es la primera ejecución
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                boolean firstRun = settings.getBoolean(KEY_FIRST_RUN, true);

                if (firstRun) {
                    Intent intent = new Intent(Caracteristica3.this, Caracteristica1.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Caracteristica3.this, inicioBotones.class);
                    startActivity(intent);
                }
                finish();
            }
        });

        boton = findViewById(R.id.boton);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Actualizar SharedPreferences para que ya no sea la primera ejecución
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean(KEY_FIRST_RUN, false);
                editor.apply();

                Intent i = new Intent(Caracteristica3.this, nombreUsuario.class);
                startActivity(i);
                finish();
            }
        });
    }
}