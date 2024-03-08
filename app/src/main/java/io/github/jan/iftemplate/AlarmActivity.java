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
    Ringtone ringtone;

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
        Uri path = Uri.parse("android.resource://io.github.jan.iftemplate/" + R.raw.homecoming);
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), path);
        ringtone.play();
        screen.stop.setOnClickListener(v -> {
            ringtone.stop();
            finish();
        });
    }

}
