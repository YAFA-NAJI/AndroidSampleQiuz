package com.example.a1200708_yafa_naji;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private TextView feedbackTextView, scoreTextView;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        feedbackTextView = findViewById(R.id.feedbackTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        resetButton = findViewById(R.id.resetButton);

        int score = getIntent().getIntExtra("score", 0);
        scoreTextView.setText(score + "/5");

        if (score >= 4) {
            feedbackTextView.setText("You Won!");
        } else {
            feedbackTextView.setText("You Lost!");
        }

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset the game
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish the ResultActivity to prevent going back to it using the back button
            }
        });
    }
}
