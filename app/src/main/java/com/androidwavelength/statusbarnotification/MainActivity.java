package com.androidwavelength.statusbarnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnNotify, btnCancel;

    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        setupListeners();
        initViews();
        setupNotificationChannels();
    }

    private void setupNotificationChannels() {
        NotificationChannelCompat notificationChannel =
                new NotificationChannelCompat.Builder(
                        "updates_notifications",
                        NotificationManager.IMPORTANCE_HIGH
                ).setDescription("Receive notifications about Bitcode batch updates.")
                        .setName("Bitcode batch updates")
                        .build();

        NotificationManagerCompat.from(MainActivity.this)
                .createNotificationChannel(notificationChannel);
    }

    private void setupListeners() {
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bitmap largeIcon = BitmapFactory.decodeResource(
                        getResources(),
                        R.drawable.flag_canada
                );

                PendingIntent actionPendingIntent =
                        PendingIntent.getActivity(
                                MainActivity.this,
                                1,
                                new Intent(MainActivity.this, HomeActivity.class),
                                0
                        );
                Notification notification =
                        new NotificationCompat.Builder(MainActivity.this)
                                .setContentTitle("Bitcode Updates")
                                .setContentText("You got call from Google!")
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setColor(Color.BLUE)
                                .setLights(Color.BLUE, 400, 500)
                                //.setSound(Uri.parse("/storage/emulated/0/bitcode"))
                                .setVibrate(new long[] {400, 300, 500, 300, 600, 300, 700, 300})
                                .setLargeIcon(largeIcon)
                                .setOngoing(true)
                                .setAutoCancel(true)
                                .setChannelId("updates_notifications")
                                .setContentIntent(actionPendingIntent)
                                .build();

                notificationManager.notify(101, notification);
            }
        });

        btnCancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        notificationManager.cancel(101);
                    }
                }
        );
    }

    private void initViews() {
        btnNotify = findViewById(R.id.btnNotify);
        btnCancel = findViewById(R.id.btnCancel);
    }
}