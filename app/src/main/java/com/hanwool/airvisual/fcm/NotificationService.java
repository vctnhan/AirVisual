package com.hanwool.airvisual.fcm;

import android.app.Notification;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.hanwool.airvisual.GlobalApplication;
import com.hanwool.airvisual.R;
import com.hanwool.airvisual.view.ui.MainActivity;

public class NotificationService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Notification notification = new NotificationCompat.Builder(GlobalApplication.getAppContext())
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setSmallIcon(R.drawable.logo)
                .build();
        NotificationManagerCompat manager = NotificationManagerCompat.from(GlobalApplication.getAppContext());
        manager.notify(123, notification);
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        
    }
}
