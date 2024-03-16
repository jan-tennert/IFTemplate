package io.github.jan.iftemplate;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import io.github.jan.iftemplate.databinding.ScreenBinding;
import io.github.jan.iftemplate.ignorieren.PreMainActivity;

public class MainActivity extends PreMainActivity implements GameActionListener {

    private TicTacToe ticTacToe;
    private TextView turnText;
    private ScreenBinding binding;
    private FieldButton[][] fieldButtons;

    //Ungefähr equivalent zu main() in normalen Java-Programmen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Die von Android erstellte Klasse von dem layout "screen.xml" wird verwendet um die Views zu erstellen (abhängig natürlich von dem Gerät, auf dem die App läuft)
        binding = ScreenBinding.inflate(getLayoutInflater());

        //Die erstellten Views werden als Content der Activity gesetzt
        setContentView(binding.getRoot());

        turnText = binding.turnTV;
        fieldButtons = new FieldButton[][] {{binding.a1, binding.a2, binding.a3}, {binding.b1, binding.b2, binding.b3}, {binding.c1, binding.c2, binding.c3}};
        binding.resetButton.setOnClickListener(v -> {
            ticTacToe.reset();
            updateTurnText();
        });
        binding.robotSettings.setOnClickListener(v -> {
            showBotSettingsDialog();
        });

        //Hier wird ein neues TicTacToe-Objekt erstellt
        ticTacToe = new TicTacToe(this, actions);
        updateTurnText();
    }

    private void showBotSettingsDialog() {
        String[] selections = new String[]{ "Deaktiviert", "Als X", "Als O"};
        new MaterialAlertDialogBuilder(this)
                .setTitle("Bot Einstellungen")
                .setSingleChoiceItems(selections, ticTacToe.getBotState().ordinal(), (dialog, which) -> {
                    ticTacToe.setBotState(BotState.values()[which]);
                })
                .setNeutralButton("Abbrechen", (dialog, which) -> {})
                .setPositiveButton("Starten", (dialog, which) -> {
                    ticTacToe.onBotGameStart();
                })
                .show();
    }

    public void fieldClicked(View view) {
        if(!(view instanceof FieldButton)) return;
        FieldButton fieldButton = (FieldButton) view;
        int row = fieldButton.getRow();
        int column = fieldButton.getColumn();
        if(!ticTacToe.onFieldClicked(row - 1, column - 1, false)) {
            updateTurnText();
        }
    }

    private void updateTurnText() {
        turnText.setText(getString(R.string.turn, ticTacToe.getCurrentPlayer()));
    }

    @Override
    public void resetFields() {
        for (FieldButton[] fieldButton : fieldButtons) {
            for (FieldButton button : fieldButton) {
                button.setText("");
            }
        }
    }

    @Override
    public void setField(int row, int column, String symbol) {
        fieldButtons[row][column].setText(symbol);
    }
}