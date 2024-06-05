package MenuLateral.ui.perfil2;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.work.Data;
import androidx.work.WorkManager;

import com.example.tareasprofcaei.R;
import com.example.tareasprofcaei.WorkManagerNoti;


import org.jetbrains.annotations.Nullable;

import java.util.Calendar;
import java.util.UUID;

import MenuLateral.menulateral;

public class perfil2 extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private EditText editTextName;
    private ImageView imageViewProfile;
    private Uri imageUri;
    String nombre;
    SharedPreferences sp;
    Button selecHora;
    TextView txthora;
    Button guardarHora, eliminarHora;
    private int minutos, hora;
    Calendar actual = Calendar.getInstance();
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

      //  establecerNotificacion();


        sp = getSharedPreferences("user_prefs", MODE_PRIVATE);
        editTextName = findViewById(R.id.editTextName);
        imageViewProfile = findViewById(R.id.imageViewProfile);
        Button botonSeleccionarImg = findViewById(R.id.botonSeleccionarImg);
        Button botonActualizar = findViewById(R.id.botonActualizar);

        botonSeleccionarImg.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE);
        });

        botonActualizar.setOnClickListener(v -> {
            nombre = editTextName.getText().toString();
            if (!nombre.isEmpty() && imageUri != null) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("Nombre", nombre);
                editor.putString("imageUri", imageUri.toString());
                editor.apply();

                Intent intent = new Intent(perfil2.this, menulateral.class);
                intent.putExtra("Nombre", nombre);
                intent.putExtra("imageUri", imageUri.toString());
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(perfil2.this, "Por favor ingresa tu nombre y selecciona una imagen", Toast.LENGTH_SHORT).show();
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
/*
    public void establecerNotificacion() {
        //para establecer la hora
        selecHora = findViewById(R.id.seleccionarHora);
        txthora = findViewById(R.id.text_hora);
        guardarHora = findViewById(R.id.establecerAlarma);
        eliminarHora = findViewById(R.id.cancelarAlarm);
        selecHora.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                hora = actual.get(Calendar.HOUR_OF_DAY);
                minutos = actual.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int h, int m) {
                        calendar.set(Calendar.HOUR_OF_DAY, h);
                        calendar.set(Calendar.MINUTE, m);
                        txthora.setText(String.format("%02d:%02d", h, m));
                    }
                }, hora, minutos, true);
                timePickerDialog.show();
            }
        });
        guardarHora.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String tag = generateKey();
                Long alertaTime = calendar.getTimeInMillis() - System.currentTimeMillis();
                int random = (int) (Math.random() * 50 + 1);
                Data data = guardarData("Notificacion workmanager", "soy un detalle", random);
                WorkManagerNoti.GuardarNoti(alertaTime, data, tag);

                Toast.makeText(perfil2.this, "Hora guardada", Toast.LENGTH_SHORT).show();
            }
        });

        eliminarHora.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                eliminarNoti("tag1");
            }
        });

    }


    private void eliminarNoti(String tag) {
        WorkManager.getInstance(this).cancelAllWorkByTag(tag);
        Toast.makeText(perfil2.this, "hora eliminada", Toast.LENGTH_SHORT).show();


    }

    private String generateKey() {
        return UUID.randomUUID().toString();
    }

    private Data guardarData(String titulo, String detalle, int id_noti) {

        return new Data.Builder()
                .putString("Titulo", titulo)
                .putString("Detalle", detalle)
                .putInt("id_noti", id_noti).build();
    }*/
}
