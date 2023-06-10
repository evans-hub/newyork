package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    private TextView titleTextView;
    private TextView abstractTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        titleTextView = findViewById(R.id.titleTextView);
        abstractTextView = findViewById(R.id.abstractTextView);

        Bundle extras = getIntent().getExtras();
        String title = extras.getString("title");
        String abstractText = extras.getString("abstract");
        titleTextView.setText(title);
        abstractTextView.setText(abstractText);
    }
}