package com.example.parcialdam;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class MyReceiver extends BroadcastReceiver {

    public static final String USUARIO_CREADO  = "isi.dam.myapplication05.EVENTO_01";
    public static final String NOTIFICATION_CHANNEL_ID = "10001";

    @Override
    public void onReceive(Context context, Intent intent) {

        String msj= intent.getExtras().getString("data");

        Intent opcion1 = new Intent(context, MainActivity.class);
        opcion1.putExtra("datox", 9999);
        PendingIntent opcion1Pi = PendingIntent.getBroadcast(context, 0, opcion1, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.utn_icon)
                .setContentIntent(opcion1Pi)
                .setContentTitle("USUARIO GUARDADO CON EXITO")
                .setContentText(msj)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Datos del "+msj))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManager notificationManager =
                context.getSystemService(NotificationManager.class);
        notificationManager.notify(99, mBuilder.build());
    }
}