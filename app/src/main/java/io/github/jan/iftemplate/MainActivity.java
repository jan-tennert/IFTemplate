package io.github.jan.iftemplate;

import android.os.Bundle;

import java.util.ArrayList;

import io.github.jan.iftemplate.ignorieren.PreMainActivity;

public class MainActivity extends PreMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hauaufgaben laden
        // Listener für das UI setzen
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Speichern von Hausaufgaben bei App Schließung
    }

}