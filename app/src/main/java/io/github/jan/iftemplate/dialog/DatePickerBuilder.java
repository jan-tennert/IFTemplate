package io.github.jan.iftemplate.dialog;

import android.content.Context;
import android.os.Build;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.Date;

import io.github.jan.iftemplate.MainActivity;

public class DatePickerBuilder extends DialogBuilder<DatePickerListener> {

    public DatePickerBuilder(Context context, String title) {
        super(context, title);
    }

    @Override
    public void show() {
        MaterialDatePicker<Long> picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(getTitle())
            .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
            .setPositiveButtonText(getPositiveButton())
            .setNegativeButtonText(getNegativeButton())
            .build();
        if (getPositiveButton() != null && getPositiveListener() != null) {
            picker.addOnPositiveButtonClickListener((w) -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && picker.getSelection() != null) {
                    getPositiveListener().onResult(new Date(picker.getSelection()));
                }
            });
        }
        if (getNegativeButton() != null && getNegativeListener() != null) {
            picker.addOnNegativeButtonClickListener((w) -> {
                getNegativeListener().run();
            });
        }
        if(getCancelListener() != null) {
            picker.addOnCancelListener((w) -> {
                getCancelListener().run();
            });
        }
        picker.show(((MainActivity) context).getSupportFragmentManager(), "date_picker");
    }

}
