package io.github.jan.iftemplate.ignorieren;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import io.github.jan.iftemplate.databinding.ScreenBinding;

public class PreMainActivity extends AppCompatActivity {

    public AppActions actions;
    public ScreenBinding screen;

    public static final String channelID = "wecker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screen = ScreenBinding.inflate(getLayoutInflater());
        setContentView(screen.getRoot());
        actions = new AppActions(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int imp = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelID, "Wecker", imp);
            channel.enableVibration(true);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
    }

}
