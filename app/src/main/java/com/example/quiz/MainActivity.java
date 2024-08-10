package com.example.quiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView, resultTextView;
    private RadioGroup answersRadioGroup;
    private Button submitBtn;
    private int correctAnswerIndex;
    private String[] questions = {
            "1) What is the capital of Maharashtra?",
            "2) Who sung the song 'Ye vatan'?",
            "3) What is the largest planet in our solar system?",
            "4) Who is the first female prime minister of India?",
            "5) What is the name of the weak zone of the earth’s crust?"
    };
    private String[][] answers = {
            {"Nagpur", "Mumbai", "Pune", "Solapur"},
            {"Diljit Dosanjh", "Sanam Puri", "Arjit singh", "Darshan Raval"},
            {"Jupiter", "Saturn", "Neptune", "Earth"},
            {"Dipika Padukon","Sonia Gandhi","Jaya Bacchan", "Indira Gandhi"},
            {"Seismic","Cosmic","Formic","Anaemic"}
    };
    private int[] correctAnswers = {1, 2, 0, 3, 0};

    private int currentQuestion = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        resultTextView = findViewById(R.id.resultTextView);
        answersRadioGroup = findViewById(R.id.answersRadioGroup);
        submitBtn = findViewById(R.id.submitBtn);

        loadQuestion();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedAnswerIndex = answersRadioGroup.indexOfChild(findViewById(answersRadioGroup.getCheckedRadioButtonId()));
                if (selectedAnswerIndex == correctAnswerIndex) {
                    score++;
                }
                currentQuestion++;
                if (currentQuestion < questions.length) {
                    loadQuestion();
                } else {
                    resultTextView.setText("Your score: " + score + "/" + questions.length);
                    submitBtn.setEnabled(false);
                }
            }
        });
    }

    private void loadQuestion() {
        questionTextView.setText(questions[currentQuestion]);
        answersRadioGroup.clearCheck();
        for (int i = 0; i < answers[currentQuestion].length; i++) {
            ((RadioButton) answersRadioGroup.getChildAt(i)).setText(answers[currentQuestion][i]);
        }
        correctAnswerIndex = correctAnswers[currentQuestion];
    }
}
