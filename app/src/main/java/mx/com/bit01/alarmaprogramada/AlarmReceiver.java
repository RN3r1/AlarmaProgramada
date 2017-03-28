package mx.com.bit01.alarmaprogramada;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

import static android.content.Context.NOTIFICATION_SERVICE;
import static mx.com.bit01.alarmaprogramada.R.string.custom_notification;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent2) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "Alarm Triggered and SMS Sent", Toast.LENGTH_LONG).show();



        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Intent i = new Intent(context, MainActivity.class);//intent al que irá cuando picas en la notificacion
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(intent);

        builder.setTicker(context.getResources().getString(custom_notification));//ticker text...?
        builder.setSmallIcon(mx.com.bit01.alarmaprogramada.R.drawable.ic_stat_custom);//icono de la notificacion
        builder.setAutoCancel(true);//auton cancel cuando le picas a la notifiacion
        Notification notification = builder.build();//presentar la notifiacion

        // Inflate the notification layout as RemoteViews
        RemoteViews contentView = new RemoteViews(context.getPackageName(), mx.com.bit01.alarmaprogramada.R.layout.notification);

        // Set text on a TextView in the RemoteViews programmatically.
        final String time = DateFormat.getTimeInstance().format(new Date()).toString();
        final String text = context.getResources().getString(mx.com.bit01.alarmaprogramada.R.string.collapsed, time);
        contentView.setTextViewText(mx.com.bit01.alarmaprogramada.R.id.textView, text);
        notification.contentView = contentView;

        if (Build.VERSION.SDK_INT >= 16) {
            RemoteViews expandedView = new RemoteViews(context.getPackageName(), mx.com.bit01.alarmaprogramada.R.layout.notification_expanded);
            notification.bigContentView = expandedView;
        }

        NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0, notification);

        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);

        Toast.makeText(context, "Alarm Triggered and SMS Sent", Toast.LENGTH_LONG).show();
    }



}