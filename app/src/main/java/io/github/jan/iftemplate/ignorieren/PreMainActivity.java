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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screen = ScreenBinding.inflate(getLayoutInflater());
        setContentView(screen.getRoot());
        actions = new AppActions(this);
    }

}
