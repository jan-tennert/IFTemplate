package io.github.jan.iftemplate;

import android.os.Bundle;

import io.github.jan.iftemplate.ignorieren.PreMainActivity;

public class MainActivity extends PreMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Dieser Code wird ausgeführt, wenn die App gestartet wird.
        System.out.println("Hallo Welt!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Dieser Code wird ausgeführt, wenn die App geschlossen wird bzw nicht mehr sichtbar wird.
        System.out.println("Tschüss Welt!");
    }

}