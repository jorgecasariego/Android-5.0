package com.androidatc.mynotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //private int NOTIF_REF = 1;
    private int idNotificacion = 0;
    private NotificationManager manager;

    private Button imagenGrandeButton;
    private Button textoGrandeButton;
    private Button inboxStyleButton;

    private int ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        imagenGrandeButton = (Button) findViewById(R.id.notificar2);
        imagenGrandeButton.setOnClickListener(this);

        textoGrandeButton = (Button) findViewById(R.id.notificar3);
        textoGrandeButton.setOnClickListener(this);

        inboxStyleButton = (Button) findViewById(R.id.notificar4);
        inboxStyleButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Notification notification =  null;

        switch (v.getId()){
            case R.id.notificar2:
                idNotificacion = 2;
                notification = bigPictureNotification();
                sendNotification(notification, idNotificacion);
                break;
            case R.id.notificar3:
                idNotificacion= 3;
                notification = bigTextNotification();
                sendNotification(notification, idNotificacion);
                break;
            case R.id.notificar4:
                idNotificacion = 4;
                notification = inboxStyleNotification();
                sendNotification(notification, idNotificacion);
                break;
        }
    }

    private Notification bigPictureNotification(){
        //1. Builder nos provee la capacidad de setear todos los campos de una notificacion y generar
        //   el contenido de las vistas usando el diseño de notification de la plataforma
        //   NotificationCompat usamos para tener compatibilidad con versiones 3.0+
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentTitle("Nueva foto subida al Servidor");
        builder.setContentText("La foto estará disponible en segundos");

        //2. Seteamos el icono de la notificacion
        builder.setSmallIcon(R.mipmap.ic_launcher);

        //3. Seteamos la hora a mostrar en la notificacion
        builder.setWhen(System.currentTimeMillis());

        //4. Creamos un icono que va a ser mostrado en la notificacion
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.asuncion);

        //5. Icono grande y abajo el chico en blanco
        builder.setLargeIcon(icon);


        //Creamos Notificacion del tipo BigPicture
        NotificationCompat.BigPictureStyle bigPicture = new NotificationCompat.BigPictureStyle()
                .bigPicture(icon)
                .bigLargeIcon(icon)
                .setBigContentTitle("Titulo cuando esta expandido")
                .setSummaryText("Resumen total de los totales");

        //6. Seteamos el estilo de la notificacion
        builder.setStyle(bigPicture);

        //7. Agregar una accion utilizando PendingIntent
        PendingIntent resultPendingIntent = agregarAccion();

        //8. Asociar el pending Intent al builder
        builder.setContentIntent(resultPendingIntent);

        // Extra: para preservar la navegacion ver el siguiente tuto:
        // http://developer.android.com/intl/es/training/notify-user/navigation.html

        //9. Enviamos la notificacion
       return builder.build();

    }

    private PendingIntent agregarAccion() {
        Intent resultado = new Intent(this, ResultadoActivity.class);

        PendingIntent resultadoPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultado,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        return resultadoPendingIntent;
    }


    //metodo para notificaciones del tipo Text Image style notification
    private Notification bigTextNotification(){
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.asuncion);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        //Esta parte de la notificacion es para cuando la notificacion no esta extendida
        builder.setContentTitle("Reduced BigText title")
                .setContentText("Reduced content")
                .setContentInfo("Info")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(icon);

        //Esta parte de la notificacion es para cuando la notificacion esta extendida
        return new NotificationCompat.BigTextStyle(builder)
                .bigText("¡Android FINAL Class!")
                .setBigContentTitle("Android ATC")
                .setSummaryText("Esta es la clase final del curso de Android ATC!!! Congratulations!")
                .build();
    }

    private Notification inboxStyleNotification(){

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("Nuevo Mensaje");
        mBuilder.setContentText("Has recibido un nuevo mensaje");
        mBuilder.setTicker("Mensaje Personal!");
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setNumber(ID++);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        String[] mensajesWhatsApp = new String[6];
        mensajesWhatsApp[0] = new String("Penny: Why is it that programmers always confuse Halloween with Christmas?");
        mensajesWhatsApp[1] = new String("Sheldom: Because 31 OCT = 25 DEC.");

        inboxStyle.setBigContentTitle("WhatsApp");

        //Agregamos los mensajes a la vista de la notificacion
        for (int i=0; i < mensajesWhatsApp.length; i++) {
            inboxStyle.addLine(mensajesWhatsApp[i]);
        }

        mBuilder.setStyle(inboxStyle);

        return mBuilder.build();

    }

    //Metodo para notificar
    // Pasos:
    // 1. Obtener una instancia de NotificationManager
    // 2. Usar el metodo notify para enviar la notificacion. Cuando se utiliza este metodo es
    //    necesario utilizar un notification ID. Podemos usar este notification ID para
    //    actualizar la notiticacion despues
    public void sendNotification(Notification notification, int idNotificacion){
        manager.notify(idNotificacion, notification);
    }

}
