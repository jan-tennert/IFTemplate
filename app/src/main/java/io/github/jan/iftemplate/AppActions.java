package io.github.jan.iftemplate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.format.DateFormat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.github.jan.iftemplate.dialog.BasicDialogBuilder;
import io.github.jan.iftemplate.dialog.DatePickerBuilder;
import io.github.jan.iftemplate.dialog.TimePickerBuilder;

public final class AppActions {

    private final Context context;

    public AppActions(Context context) {
        this.context = context;
    }

    public BasicDialogBuilder alertDialog(String title, String message) {
        return new BasicDialogBuilder(context, title).setMessage(message);
    }

    public TimePickerBuilder timePicker(String title) {
        return new TimePickerBuilder(context, title);
    }

    public DatePickerBuilder datePicker(String title) {
        return new DatePickerBuilder(context, title);
    }

    @SuppressLint("NewApi")
    public String formatDate(Date date, String pattern) {
        return (String) DateFormat.format(pattern, date);
    }

    public Date parseDate(String date, String pattern) {
        try {
            return (Date) new SimpleDateFormat(pattern, Locale.getDefault()).parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public File getDataFile(String filename) {
        return new File(context.getFilesDir(), filename);
    }

    public String readFile(File file) {
        //Read the data from the file and return it
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                 = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resultStringBuilder.toString();
    }

    public void writeFile(File file, String data) {
        try(java.io.FileWriter writer = new java.io.FileWriter(file);) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void vibrate(int duration, int strength) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(duration, strength));
        } else {
            v.vibrate(duration);
        }
    }

}
