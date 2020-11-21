package com.example.parcialdam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button guardar;
    private EditText nombre;
    private static final ArrayList<Usuario> listaUsiarios = new ArrayList<>();
    private BroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        guardar = findViewById(R.id.guardar);
        nombre = findViewById(R.id.nombre);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this,R.array.values, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        br = new MyReceiver();
        IntentFilter filter = new IntentFilter(MyReceiver.USUARIO_CREADO);
        this.registerReceiver(br, filter);
        crearCanal(this);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario u = new Usuario(nombre.getText().toString(),spinner.getSelectedItem().toString());
                Toast.makeText(MainActivity.this,"El usuario se está guardando, aguarde unos segundos",Toast.LENGTH_LONG).show();
                new GuardarUsuario().execute(u);
                Intent i = new Intent(MainActivity.this,MyIntentService.class);
                i.putExtra("Notificacion",u.toString());
                startService(i);
            }
        });
    }

    class GuardarUsuario extends AsyncTask<Usuario,Void,Void> {

        @Override
        protected Void doInBackground(Usuario... usuarios) {
            listaUsiarios.add(usuarios[0]);
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(br);
    }

    public void crearCanal(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE ) ;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            int importance = NotificationManager.IMPORTANCE_HIGH ;
            NotificationChannel notificationChannel = new NotificationChannel(  MyReceiver.NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
            notificationManager.createNotificationChannel(notificationChannel) ;
        }
    }
}