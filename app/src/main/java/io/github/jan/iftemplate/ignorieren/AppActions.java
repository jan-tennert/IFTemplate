package io.github.jan.iftemplate.ignorieren;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.github.jan.iftemplate.dialog.BasicDialogBuilder;
import io.github.jan.iftemplate.dialog.DatePickerBuilder;
import io.github.jan.iftemplate.dialog.TimePickerBuilder;

public final class AppActions {

    private final Context context;
    private final AlarmManager alarmManager;
    private final SharedPreferences preferences;

    public AppActions(Context context) {
        this.context = context;
        this.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.preferences = context.getSharedPreferences("app", Context.MODE_PRIVATE);
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

    public void setAlarm(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        preferences.edit().putInt("hour", hour).putInt("minute", minute).apply();
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public void cancelAlarm() {
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        alarmManager.cancel(pendingIntent);
        preferences.edit().remove("hour").remove("minute").apply();
    }

    public Time getAlarmTime() {
        int hour = preferences.getInt("hour", 0);
        int minute = preferences.getInt("minute", 0);
        return new Time(hour, minute);
    }

    public MediaPlayer createMediaPlayer(int resId) {
        return MediaPlayer.create(context, resId);
    }

}
