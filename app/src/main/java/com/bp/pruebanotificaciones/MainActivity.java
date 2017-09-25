package com.bp.pruebanotificaciones;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.app.Notification.VISIBILITY_PUBLIC;

public class MainActivity extends AppCompatActivity {

    private Button boton;
    private int notificationID = 1;
    private int idPeticion = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = (Button) findViewById(R.id.addNotification);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createSimpleNotification();
            }
        });
    }

    protected void createSimpleNotification(){

        /*NotificationManagerCompat permite crear notificaciones compatibles con todas las versiones de Android*/
        final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        /*Se crea un Intent normal para el PendingIntent, cuando se pulse la notificación se abrirá de nuevo la misma vista*/
        final Intent notificationIntent = new Intent(this,MainActivity.class);
        /*PendingIntent permite abrir una vista cuando se pulse la notificación.
        * En este caso se utiliza getActivity, que apunta a una actividad.
        * getBroadcast: produce un BroadcastReceiver.
        * getService: ejecuta un servicio.*/
        final PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,idPeticion,
                notificationIntent,PendingIntent.FLAG_ONE_SHOT); //Flag_one_shot: el intent solo podrá usarse una vez

        /*NotificationCompat permite crear una notificación que funcionará igual en cualquier versión
        * de Android*/
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                //Tiempo que se mostrará la notificación.
                .setWhen(System.currentTimeMillis())
                //Texto cuando se produce la notificación
                .setTicker("Nueva notificación")
                //Icono de la notificación
                .setSmallIcon(R.drawable.ic_launcher_round)
                //Titulo de la notificación
                .setContentTitle("Titulo de Notificación")
                //Descripción de la notificación
                .setContentText("Descripción de Notificación")
                //Añade una acción a la notificación, al pulsar sobre ella abrirá la vista definida en notificationIntent
                .addAction(new NotificationCompat.Action(R.drawable.ic_launcher_round,"Acción 1",notificationPendingIntent))
                .addAction(new NotificationCompat.Action(R.drawable.ic_launcher_round,"Acción 2",notificationPendingIntent))
                //Establece la importancia de la notificación
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                //Permite ver la notificación desde la pantalla de bloqueo
                .setVisibility(VISIBILITY_PUBLIC)
                //Permite crear notificaciones con texto e imagenes grandes
                //.setStyle(new NotificationCompat.BigTextStyle()
                //.bigText("Texto muy muy muy muy grandeee"))
                //Inicia el intent
                .setContentIntent(notificationPendingIntent);

        /*Desencadena la notificación con notify*/
        notificationManager.notify(notificationID, notificationBuilder.build());
    }
}
