package io.github.jan.iftemplate.dialog;

import android.content.Context;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import io.github.jan.iftemplate.MainActivity;

public class TimePickerBuilder extends DialogBuilder<TimePickerListener> {

    public TimePickerBuilder(Context context, String title) {
        super(context, title);
    }

    @Override
    public void show() {
        MaterialTimePicker picker = new MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setTitleText(getTitle())
            .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
            .setNegativeButtonText(getNegativeButton())
            .setPositiveButtonText(getPositiveButton())
            .build();
        if(getPositiveButton() != null && getPositiveListener() != null) {
            picker.addOnPositiveButtonClickListener((w) -> {
                getPositiveListener().onResult(picker.getHour(), picker.getMinute());
            });
        }
        if(getNegativeButton() != null && getNegativeListener() != null) {
            picker.addOnNegativeButtonClickListener((w) -> {
                getNegativeListener().run();
            });
        }
        if(getCancelListener() != null) {
            picker.addOnCancelListener((w) -> {
                getCancelListener().run();
            });
        }
        picker.show(((MainActivity) context).getSupportFragmentManager(), "time_picker");
    }

}
