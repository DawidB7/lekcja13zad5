package com.example.zad5l13;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String NAME_KEY = "UserName";
    private static final String NOTIF_KEY = "NotificationsEnabled";

    private TextView textViewGreeting, textViewNotifications;
    private Button buttonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewGreeting = findViewById(R.id.textViewGreeting);
        textViewNotifications = findViewById(R.id.textViewNotifications);
        buttonSettings = findViewById(R.id.buttonSettings);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String userName = prefs.getString(NAME_KEY, "gościu");
        boolean notificationsEnabled = prefs.getBoolean(NOTIF_KEY, false);

        textViewGreeting.setText("Witaj, " + userName + "!");
        textViewNotifications.setText("Powiadomienia: " + (notificationsEnabled ? "Włączone" : "Wyłączone"));

        buttonSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String userName = prefs.getString(NAME_KEY, "gościu");
        boolean notificationsEnabled = prefs.getBoolean(NOTIF_KEY, false);

        textViewGreeting.setText("Witaj, " + userName + "!");
        textViewNotifications.setText("Powiadomienia: " + (notificationsEnabled ? "Włączone" : "Wyłączone"));
    }
}
