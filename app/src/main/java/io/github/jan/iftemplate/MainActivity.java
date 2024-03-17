package io.github.jan.iftemplate;

import android.os.Bundle;

import io.github.jan.iftemplate.ignorieren.PreMainActivity;
import io.github.jan.iftemplate.ignorieren.Time;

public class MainActivity extends PreMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLocationPermission();
        //On Click Listener hinzufügen und dann mit actions.getWeatherData() die Wetterdaten abrufen
    }

}