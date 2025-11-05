package com.example.zad5l13;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String NAME_KEY = "UserName";
    private static final String NOTIF_KEY = "NotificationsEnabled";

    private EditText editTextName;
    private Switch switchNotifications;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editTextName = findViewById(R.id.editTextName);
        switchNotifications = findViewById(R.id.switchNotifications);
        buttonSave = findViewById(R.id.buttonSave);

        // --- Wczytanie istniejących ustawień ---
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedName = prefs.getString(NAME_KEY, "");
        boolean savedSwitch = prefs.getBoolean(NOTIF_KEY, false);

        editTextName.setText(savedName);
        switchNotifications.setChecked(savedSwitch);

        // --- Zapis po kliknięciu ---
        buttonSave.setOnClickListener(v -> {
            SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString(NAME_KEY, editTextName.getText().toString());
            editor.putBoolean(NOTIF_KEY, switchNotifications.isChecked());
            editor.apply();

            Toast.makeText(SettingsActivity.this, "Ustawienia zapisane!", Toast.LENGTH_SHORT).show();
            finish(); // Powrót do MainActivity
        });
    }
}
