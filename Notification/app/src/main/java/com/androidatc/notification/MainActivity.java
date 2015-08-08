package com.androidatc.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {


    private CharSequence mTitle;

    private Button maxPriority;
    private Button highPriority;
    private Button lowPriority;
    private Button minPriority;
    private Button defaultPriority;
    private Button oldPriority;
    private Button bigText;
    private Button bigImage;
    private Button inboxTypeNotification;

    private int NOTIF_REF = 1;
    private NotificationManager manager;
    private int count = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        maxPriority = (Button) findViewById(R.id.maxPriority);
        maxPriority.setOnClickListener(this);

        highPriority = (Button) findViewById(R.id.highPriority);
        highPriority.setOnClickListener(this);

        lowPriority = (Button) findViewById(R.id.lowPriority);
        lowPriority.setOnClickListener(this);

        minPriority = (Button) findViewById(R.id.minPriority);
        minPriority.setOnClickListener(this);

        defaultPriority = (Button) findViewById(R.id.defaultPriority);
        defaultPriority.setOnClickListener(this);

        oldPriority = (Button) findViewById(R.id.oldPriority);
        oldPriority.setOnClickListener(this);

        bigText = (Button) findViewById(R.id.bigText);
        bigText.setOnClickListener(this);

        bigImage = (Button) findViewById(R.id.bigImage);
        bigImage.setOnClickListener(this);

        inboxTypeNotification = (Button) findViewById(R.id.inboxTypeNotification);
        inboxTypeNotification.setOnClickListener(this);

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    }

    @Override
    public void onClick(View v) {
        Notification notification =  null;
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentText("Android Notifications");

        switch (v.getId()){
            case R.id.maxPriority:
                builder.setContentTitle("Maximun priority Notification")
                        .setPriority(Notification.PRIORITY_MAX);
                sendNotification(builder.build());
                break;
            case R.id.highPriority:
                builder.setContentTitle("High priority notification")
                        .setPriority(Notification.PRIORITY_HIGH);
                sendNotification(builder.build());
                break;
            case R.id.lowPriority:
                builder.setContentTitle("Low priority notification")
                        .setPriority(Notification.PRIORITY_LOW);
                sendNotification(builder.build());
                break;

            case R.id.minPriority:
                builder.setContentTitle("Minimun priority notification")
                        .setPriority(Notification.PRIORITY_MIN);
                sendNotification(builder.build());
                break;
            case R.id.defaultPriority:
                notification = getDefaultNotification(builder);
                sendNotification(notification);
                break;
            case R.id.oldPriority:
                notification = getDefaultNotification(builder);
                sendNotification(notification);
                break;
            case R.id.bigText:
                notification = getBigTextStyle(builder);
                sendNotification(notification);
                break;
            case R.id.bigImage:
                notification = getBigPictureStyle(builder);
                sendNotification(notification);
                break;
            case R.id.inboxTypeNotification:
                inboxStyleNotifications();
                break;
        }
    }

    public void sendNotification(Notification notification){
        manager.notify(NOTIF_REF++, notification);
    }

    public Notification getDefaultNotification(Notification.Builder builder){
        builder
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Default Notification")
                .setContentText("This is a random text for default type notification")
                .setContentInfo("Info");

        return builder.build();
    }


    //metodo para notificaciones del tipo Big Image style notification
    private Notification getBigPictureStyle(Notification.Builder builder){
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.paraguay);

        builder.setContentTitle("Reduced BigPicture title")
                .setContentText("Reduced content")
                .setContentInfo("Info")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(icon);

        return new Notification.BigPictureStyle(builder)
                .bigPicture(icon)
                .bigLargeIcon(icon)
                .setBigContentTitle("Expanded BigPicture title")
                .setSummaryText(getResources().getString(R.string.summary_text))
                .build();
    }

    //metodo para notificaciones del tipo Text Image style notification
    private Notification getBigTextStyle(Notification.Builder builder){
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.paraguay);

        builder.setContentTitle("Reduced BigText title")
                .setContentText("Reduced content")
                .setContentInfo("Info")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(icon);

        return new Notification.BigTextStyle(builder)
                .bigText("")
                .setBigContentTitle("Android ATC")
                .setSummaryText(getResources().getString(R.string.summary_text))
                .build();
    }

    private void inboxStyleNotifications(){
        int ID = 1;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        inboxStyle.setSummaryText(getResources().getString(R.string.summary_text));
        mBuilder.setStyle(inboxStyle);
        mBuilder.setNumber(1);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(ID, mBuilder.build());

    }


    private Notification getOldNotifications(){
        Notification notification = new Notification(R.mipmap.ic_launcher,
                null, System.currentTimeMillis());

        notification.setLatestEventInfo(this, "Old Title", "Old notification content text",
                PendingIntent.getActivity(this, 0, new Intent(), 0));

        return notification;
    }


}
