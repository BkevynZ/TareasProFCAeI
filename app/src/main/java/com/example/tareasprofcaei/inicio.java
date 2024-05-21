package com.example.tareasprofcaei;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import pl.droidsonroids.gif.GifImageView;

public class inicio extends AppCompatActivity {
    private final int calendar=1;
    private final int perfil=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        GifImageView botonImagen = findViewById(R.id.botonCrear);
        botonImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes realizar las acciones que deseas cuando se haga clic en el botón de imagen
                // Por ejemplo, iniciar una nueva actividad
                Intent intent = new Intent(inicio.this, crearTarea.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });



        MeowBottomNavigation bottomNavigation = findViewById(R.id.bottomNavigation);


        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_calendar_month_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_account_circle_24));
        bottomNavigation.show(1,true);




    }
}