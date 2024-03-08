package io.github.jan.iftemplate.dialog;

import android.content.Context;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class BasicDialogBuilder extends DialogBuilder<Runnable> {

    private String message;

    public BasicDialogBuilder(Context context, String title) {
        super(context, title);
    }

    public BasicDialogBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public void show() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context)
            .setTitle(getTitle())
            .setMessage(message);
        if (getPositiveButton() != null) {
            builder.setPositiveButton(getPositiveButton(), (dialog, which) -> {
                if(getNegativeListener() != null) {
                    getNegativeListener().run();
                }
            });
        }
        if (getNegativeButton() != null) {
            builder.setNegativeButton(getNegativeButton(), (dialog, which) -> {
                if(getNegativeListener() != null) {
                    getNegativeListener().run();
                }
            });
        }
        if(getCancelListener() != null) {
            builder.setOnCancelListener(dialog -> getCancelListener().run());
        }
        builder.show();
    }
}
