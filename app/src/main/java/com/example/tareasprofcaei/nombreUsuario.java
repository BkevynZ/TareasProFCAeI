package com.example.tareasprofcaei;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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

        editTextName = findViewById(R.id.editTextName);
        imageViewProfile = findViewById(R.id.imageViewProfile);
        Button buttonSelectImage = findViewById(R.id.buttonSelectImage);
        Button buttonSave = findViewById(R.id.buttonSave);

        buttonSelectImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE);
        });

        buttonSave.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            Intent intent = new Intent(nombreUsuario.this, menulateral.class);
            intent.putExtra("name", name);
            if (imageUri != null) {
                intent.putExtra("imageUri", imageUri.toString());
            }
            startActivity(intent);
            finish(); // Cierra la actividad actual
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

