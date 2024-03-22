package com.example.a1200708_yafa_naji;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView scoreTextView, timeLeftTextView, questionTextView;
    private Button choice1Button, choice2Button, choice3Button, choice4Button;
    private int score = 0;
    private int questionNumber = 0;
    private CountDownTimer countDownTimer;

    private String[] questions = {
            "Question 1: How many colors are there in the rainbow?",
            "Question 2: What is the largest continent in the world?",
            "Question 3: how many planets are there in the solar system?",
            "Question 4: What is the chemical symbol for water?",
            "Question 5: Which country is known as the Land of the Rising Sun?"
    };

    private String[][] answers = {
            {"7", "6", "8", "10"},
            {"Africa", "Europe", "Asia", "South America"},
            {"10", "8", "6", "11"},
            {"H2O", "CO2", "O2", "N2"},
            {"China", "Japan", "India", "South Korea"}
    };

    private String[] correctAnswers = {"7", "Asia", "8", "H2O", "Japan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTextView = findViewById(R.id.scoreTextView);
        timeLeftTextView = findViewById(R.id.timeLeftTextView);
        questionTextView = findViewById(R.id.questionTextView);
        choice1Button = findViewById(R.id.choice1Button);
        choice2Button = findViewById(R.id.choice2Button);
        choice3Button = findViewById(R.id.choice3Button);
        choice4Button = findViewById(R.id.choice4Button);

        updateQuestion();

        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(choice1Button.getText().toString());
            }
        });

        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(choice2Button.getText().toString());
            }
        });

        choice3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(choice3Button.getText().toString());
            }
        });

        choice4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(choice4Button.getText().toString());
            }
        });

        startNextQuestionTimer();
    }

    private void startNextQuestionTimer() {
        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftTextView.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                // Time's up, move to the next question
                goToNextQuestion();
            }
        }.start();
    }

    private void checkAnswer(String selectedAnswer) {
        countDownTimer.cancel(); // Cancel the timer when an answer is selected
        if (selectedAnswer.equals(correctAnswers[questionNumber])) {
            score++;
            scoreTextView.setText(String.valueOf(score));
        }
        goToNextQuestion();
    }

    private void goToResultActivity() {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
        finish();
    }

    private void goToNextQuestion() {
        questionNumber++;
        if (questionNumber < questions.length) {
            updateQuestion();
            startNextQuestionTimer(); // Start timer for the next question
        } else {
            goToResultActivity();
        }
    }

    private void updateQuestion() {
        questionTextView.setText(questions[questionNumber]);
        choice1Button.setText(answers[questionNumber][0]);
        choice2Button.setText(answers[questionNumber][1]);
        choice3Button.setText(answers[questionNumber][2]);
        choice4Button.setText(answers[questionNumber][3]);
    }
}
