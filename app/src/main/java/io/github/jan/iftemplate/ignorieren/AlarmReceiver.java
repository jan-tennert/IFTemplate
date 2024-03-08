package io.github.jan.iftemplate.ignorieren;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import io.github.jan.iftemplate.AlarmActivity;
import io.github.jan.iftemplate.MainActivity;
import io.github.jan.iftemplate.R;

public class AlarmReceiver extends BroadcastReceiver {

    @SuppressLint("MissingPermission") //wird beim setzen vom Wecker angefragt
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("Wecker wurde ausgelöst!");
        Intent nextActivity = new Intent(context, AlarmActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, nextActivity, PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, MainActivity.channelID)
            .setSmallIcon(R.drawable.baseline_notifications_active_24)
            .setContentTitle("Wecker")
            .setContentText("Der Wecker wurde ausgelöst!")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setFullScreenIntent(pendingIntent, true);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123, builder.build());
    }

}
