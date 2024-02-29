package io.github.jan.iftemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.File;
import java.io.IOException;

import io.github.jan.iftemplate.databinding.ScreenBinding;

public class MainActivity extends AppCompatActivity {

    AppActions actions;
    ScreenBinding screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screen = ScreenBinding.inflate(getLayoutInflater());
        setContentView(screen.getRoot());
        actions = new AppActions(this);
        try {
            main();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Hier wird euer Code ausgeführt
     */
    public void main() throws IOException {
        File dateFile = actions.getDataFile("date.txt");
        if(!dateFile.exists()) {
            dateFile.createNewFile();
        }
        String date = actions.readFile(dateFile);
        System.out.println(date);
        screen.button4.setOnClickListener(v -> {
            actions.datePicker("Datum wählen")
                .setOnSuccessListener((date_) -> {
                  //  actions.writeFile(dateFile, actions.formatDate(date, "dd.MM.yyyy"));
                })
                .show();
        });
    }

}