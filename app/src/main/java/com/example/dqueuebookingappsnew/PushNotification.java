package com.example.dqueuebookingappsnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PushNotification extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notification);

        button= findViewById(R.id.btnSubmit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotification();
            }
        });
    }


    private void addNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("")
                .setContentText("");

         //After click noti, akan pergi mana??
        Intent notificationIntent = new Intent(this,SecondActivity.class);

          PendingIntent contentIntent=PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
          builder.setContentIntent(contentIntent);
          NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
          manager.notify(0,builder.build());
    }
}