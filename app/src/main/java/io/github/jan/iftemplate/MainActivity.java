package io.github.jan.iftemplate;

import android.os.Bundle;

import io.github.jan.iftemplate.ignorieren.PreMainActivity;
import io.github.jan.iftemplate.ignorieren.Time;

public class MainActivity extends PreMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Time prevTime = actions.getAlarmTime();
        screen.time.setText(actions.formatTime(prevTime));
        screen.setAlarm.setOnClickListener((v) -> {
            actions.timePicker("Wähle eine Zeit aus")
                .setOnSuccessListener((hour, minute) -> {
                    actions.setAlarm(hour, minute);
                    screen.time.setText(actions.formatTime(new Time(hour, minute)));
                    actions.alertDialog("Wecker", "Der Wecker wurde gestellt!").setPositiveButton("Ok").show();
                })
                .show();
        });
    }

}