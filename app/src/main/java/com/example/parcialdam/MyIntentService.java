package com.example.parcialdam;

import android.app.IntentService;
import android.content.Intent;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String msj = intent.getStringExtra("Notificacion");

        try {
            Thread.sleep(3500);
            Intent i = new Intent();
            i.putExtra("data",msj);
            i.setAction(MyReceiver.USUARIO_CREADO);
            sendBroadcast(i);
            this.sendBroadcast(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
