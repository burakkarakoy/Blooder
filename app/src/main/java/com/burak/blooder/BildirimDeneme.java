package com.burak.blooder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;

public class BildirimDeneme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bildirim_deneme);

        final Button buton1 = (Button) findViewById(R.id.button1);
        final Button buton2 = (Button) findViewById(R.id.button2);


    }

    public void İkinciBildirim(View v) {
        NotificationManager bildirimYönetici = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification bildirim = new Notification.Builder(getApplicationContext()).
                setTicker("bildirim uygulaması").
                setContentTitle("başlık").
                setContentText("Detaylar").
                setContentIntent(PendingIntent.getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(), BildirimeBasinca.class), 0)).
                setSmallIcon(R.mipmap.ic_144x144);


        bildirimYönetici.notify("ikinci bildirim", 0, bildirim);


    }
}
