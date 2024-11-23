package com.example.studysynccalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            // Find the sign-in button by ID
            Button signInButton = findViewById(R.id.signin);

            // Set an OnClickListener on the button
            signInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create an Intent to start the CalendarActivity
                    Intent intent = new Intent(LoginActivity.this, CalendarActivity.class);
                    // Start the new activity
                    startActivity(intent);
                }
            });
        }
    }