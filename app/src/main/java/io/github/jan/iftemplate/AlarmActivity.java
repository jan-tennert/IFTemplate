package io.github.jan.iftemplate;

import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.github.jan.iftemplate.databinding.AlarmBinding;

public class AlarmActivity extends AppCompatActivity {

    AlarmBinding screen;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true);
            setTurnScreenOn(true);
        }
        screen = AlarmBinding.inflate(getLayoutInflater());
        setContentView(screen.getRoot());

        main();
    }

    // Hier wird euer Code ausgeführt, wenn der Wecker ausgelöst wird
    public void main() {
        //für ein eigenen sound, datei in den "raw" ordner und hier die id ändern
        mediaPlayer = MediaPlayer.create(this, R.raw.homecoming);
        mediaPlayer.start();
        screen.stop.setOnClickListener(v -> {
            mediaPlayer.stop();
            finish();
        });
    }

}
