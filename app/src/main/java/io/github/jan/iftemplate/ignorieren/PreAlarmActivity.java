package io.github.jan.iftemplate.ignorieren;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.github.jan.iftemplate.databinding.AlarmBinding;

public class PreAlarmActivity extends AppCompatActivity {

    public AlarmBinding screen;
    public AppActions actions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true);
            setTurnScreenOn(true);
        }
        screen = AlarmBinding.inflate(getLayoutInflater());
        actions = new AppActions(this);
        setContentView(screen.getRoot());
    }
}
