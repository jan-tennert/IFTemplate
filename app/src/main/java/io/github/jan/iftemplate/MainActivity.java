package io.github.jan.iftemplate;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import io.github.jan.iftemplate.databinding.ScreenBinding;
import io.github.jan.iftemplate.ignorieren.AppActions;

public class MainActivity extends AppCompatActivity {

    AppActions actions;
    ScreenBinding screen;

    public static final String channelID = "wecker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screen = ScreenBinding.inflate(getLayoutInflater());
        setContentView(screen.getRoot());
        actions = new AppActions(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            int imp = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelID, "Wecker", imp);
            channel.enableVibration(true);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        checkNotificationPermission();
        try {
            main();
        } catch (Exception e) { //eigentlich sollte man das nicht machen, aber egal
            throw new RuntimeException(e);
        }
    }

    public void checkNotificationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }
    }

    /**
     * Hier wird euer Code ausgeführt
     */
    public void main() {
        screen.setAlarm.setOnClickListener((v) -> {
            actions.timePicker("Wähle eine Zeit aus")
                .setOnSuccessListener((hour, minute) -> {
                    actions.setAlarm(hour, minute);
                    actions.alertDialog("Wecker", "Der Wecker wurde gestellt!").setPositiveButton("Ok").show();
                })
                .show();
        });
    }

}