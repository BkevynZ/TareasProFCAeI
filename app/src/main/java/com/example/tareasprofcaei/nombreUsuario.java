package com.example.tareasprofcaei;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.jetbrains.annotations.Nullable;

import MenuLateral.menulateral;

public class nombreUsuario extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private EditText editTextName;
    private ImageView imageViewProfile;
    private Uri imageUri;
    String nombre;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nombre_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sp = getSharedPreferences("user_prefs", MODE_PRIVATE);
        editTextName = findViewById(R.id.editTextName);
        imageViewProfile = findViewById(R.id.imageViewProfile);
        Button botonSeleccionarImg = findViewById(R.id.botonSeleccionarImg);
        Button botonGuardar = findViewById(R.id.botonGuardar);

        botonSeleccionarImg.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE);
        });

        botonGuardar.setOnClickListener(v -> {
             nombre = editTextName.getText().toString();
            if (!nombre.isEmpty() && imageUri != null) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("Nombre", nombre);
                editor.putString("imageUri", imageUri.toString());
                editor.apply();

                Intent intent = new Intent(nombreUsuario.this, menulateral.class);
                intent.putExtra("Nombre", nombre);
                intent.putExtra("imageUri", imageUri.toString());
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(nombreUsuario.this, "Por favor ingresa tu nombre y selecciona una imagen", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageViewProfile.setImageURI(imageUri);
        }
    }

    }

