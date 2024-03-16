package io.github.jan.iftemplate.ignorieren;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.format.DateFormat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.jan.iftemplate.dialog.BasicDialogBuilder;
import io.github.jan.iftemplate.dialog.DatePickerBuilder;
import io.github.jan.iftemplate.dialog.TimePickerBuilder;

public final class AppActions {

    private final Context context;
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

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

    public void downloadImage(String url, ImageBitmapListener listener) {
        executorService.execute(() -> {
            try {
                URL uri = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(input);
                Activity activity = (Activity) context;
                activity.runOnUiThread(() -> listener.onSuccess(bitmap));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @SuppressLint("NewApi")
    public String formatDate(Date date, String pattern) {
        return (String) DateFormat.format(pattern, date);
    }

    public String formatTime(Time time) {
        return String.format(Locale.getDefault(), "%02d:%02d", time.getHour(), time.getMinute());
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

    public MediaPlayer createMediaPlayer(int resId) {
        return MediaPlayer.create(context, resId);
    }

}
