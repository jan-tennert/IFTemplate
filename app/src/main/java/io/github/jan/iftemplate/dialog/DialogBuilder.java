package io.github.jan.iftemplate.dialog;

import android.content.Context;

public abstract class DialogBuilder<Listener> {

    private String title;
    private Listener listener;
    private Runnable negativeListener;
    private Runnable cancelListener;
    private String positiveButton;
    private String negativeButton;
    Context context;

    public DialogBuilder(Context context, String title) {
        this.context = context;
        positiveButton = "OK";
        this.title = title;
    }

    public DialogBuilder<Listener> setPositiveButton(String positiveButton) {
        this.positiveButton = positiveButton;
        return this;
    }

    public DialogBuilder<Listener> setNegativeButton(String negativeButton) {
        this.negativeButton = negativeButton;
        return this;
    }

    public DialogBuilder<Listener> setTitle(String title) {
        this.title = title;
        return this;
    }

    public DialogBuilder<Listener> setOnSuccessListener(Listener listener) {
        this.listener = listener;
        return this;
    }

    public DialogBuilder<Listener> setOnNegativeListener(Runnable listener) {
        this.negativeListener = listener;
        return this;
    }

    public DialogBuilder<Listener> setOnCancelListener(Runnable listener) {
        this.cancelListener = listener;
        return this;
    }

    public Runnable getCancelListener() {
        return cancelListener;
    }

    public Runnable getNegativeListener() {
        return negativeListener;
    }

    public String getTitle() {
        return title;
    }

    public Listener getPositiveListener() {
        return listener;
    }

    public String getPositiveButton() {
        return positiveButton;
    }

    public String getNegativeButton() {
        return negativeButton;
    }

    public abstract void show();

}
