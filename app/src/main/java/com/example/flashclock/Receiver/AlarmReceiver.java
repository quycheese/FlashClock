package com.example.flashclock.Receiver;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.flashclock.R;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.tone2);
        mediaPlayer.start();

        Uri sound = Uri.parse("android.resource://" + "/" +R.raw.tone2);
        //Notification

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher );


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "BaoThuc")
                .setContentTitle("Chuông Báo")
                .setContentText(" Bao Thucc")
                .setSmallIcon(R.drawable.ic_notification)
                .setAutoCancel(true)
                .setSound(sound)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123,builder.build());
    }
}
