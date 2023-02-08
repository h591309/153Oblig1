package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oblig1.databinding.ActivityQuizBinding;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityQuizBinding binding;
    private int score = 0;
    private int correctButton;

    private int numberOfQuestions;

    private boolean hardMode = false;
    private EntriesSingleton db = EntriesSingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        numberOfQuestions = 0;
        hardMode = (boolean) getIntent().getBooleanExtra("hard_mode", false);

        wrongOrRightText();
        Log.d("CORRECT BUTTON", "onCreate: " + correctButton);
    }

    @Override
    protected void onStart() {
        super.onStart();
        quizQuest();
        Log.d("HARMODE", "onStart: " + (hardMode ? "on" : "off"));
    }

    private void quizQuest() {
        correctButton = (int) Math.round((Math.random() * 2) + 1);
        numberOfQuestions++;
        Log.d("CORRECT BUTTON", "onStart: " + correctButton);
        Entry question = pickRandomQuizQuestion();
        String wrongName1 = pickRandomQuizQuestion().getName();
        String wrongName2 = pickRandomQuizQuestion().getName();
        boolean done = false;
        while(!done) {
            if(wrongName1 == question.getName()) {
                wrongName1 = pickRandomQuizQuestion().getName();
            } else if(wrongName2 == question.getName()) {
                wrongName2 = pickRandomQuizQuestion().getName();
            } else if(wrongName1 == wrongName2) {
                wrongName1 = pickRandomQuizQuestion().getName();
            } else {
                done = true;
            }
        }

        TextView textViewQuestionNumber = (TextView) findViewById(R.id.textViewQuestionNumber);
        textViewQuestionNumber.setText(getResources().getString(R.string.question_number) + numberOfQuestions);

        Button btn1 = findViewById(R.id.quizBtn1);
        Button btn2 = findViewById(R.id.quizBtn2);
        Button btn3 = findViewById(R.id.quizBtn3);
        if(hardMode) {
            TextView modeTxt = (TextView) findViewById(R.id.textViewMode);
            modeTxt.setText("Hard mode");
        }

        switch (correctButton) {
            case 1:
                btn1.setText(question.getName());
                btn2.setText(wrongName1);
                btn3.setText(wrongName2);
                //btn1.setBackgroundColor(getResources().getColor(R.color.correct_green, getTheme()));
                //btn2.setBackgroundColor(getResources().getColor(R.color.red, getTheme()));
                //btn3.setBackgroundColor(getResources().getColor(R.color.red, getTheme()));
                break;
            case 2:
                btn2.setText(question.getName());
                btn1.setText(wrongName1);
                btn3.setText(wrongName2);
                //btn1.setBackgroundColor(getResources().getColor(R.color.red, getTheme()));
                //btn2.setBackgroundColor(getResources().getColor(R.color.correct_green, getTheme()));
                //btn3.setBackgroundColor(getResources().getColor(R.color.red, getTheme()));
                break;
            case 3:
                btn3.setText(question.getName());
                btn2.setText(wrongName1);
                btn1.setText(wrongName2);
                // btn1.setBackgroundColor(getResources().getColor(R.color.red, getTheme()));
                //btn2.setBackgroundColor(getResources().getColor(R.color.red, getTheme()));
                //btn3.setBackgroundColor(getResources().getColor(R.color.correct_green, getTheme()));
                break;
        }
        ImageView img = findViewById(R.id.imageViewQuiz);
        img.setImageBitmap(question.getImg());

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        TextView text = (TextView) findViewById(R.id.textViewQuiz);
        text.setText("Score: " + score);
    }

    private Entry pickRandomQuizQuestion() {
        int randomNumber = (int) Math.round(Math.random() * (db.getEntries().size()-1));
        EntriesSingleton db = EntriesSingleton.getInstance();
        return db.getEntries().getEntry(randomNumber);
    }

    @Override
    public void onClick(View v) {
        boolean positive = false;
        int pointsToAdd = 0;
        switch(v.getId()){
            case R.id.quizBtn1:
                if(correctButton == 1) {
                    pointsToAdd += 10;
                }

                else
                    pointsToAdd -= 5;
                break;
            case R.id.quizBtn2:
                if(correctButton == 2)
                    pointsToAdd += 10;
                else
                    pointsToAdd -= 5;
                break;
            case R.id.quizBtn3:
                if(correctButton == 3)
                    pointsToAdd += 10;
                else
                    pointsToAdd -= 5;
                break;
        }
        score += pointsToAdd;
        TextView textViewPointsAdded = findViewById(R.id.textViewPointsAdded);
        if(pointsToAdd > 0) {
            textViewPointsAdded.setTextColor(getResources().getColor(R.color.correct_green, getTheme()));
            textViewPointsAdded.setText(R.string.right_answer);
        } else {
            textViewPointsAdded.setTextColor(getResources().getColor(R.color.red, getTheme()));
            textViewPointsAdded.setText(R.string.wrong_answer);
        }
        textViewAnimation();

        TextView text = (TextView) findViewById(R.id.textViewQuiz);
        text.setText("Score: " + score);

        quizQuest();

    }

    private void textViewAnimation() {
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
        AlphaAnimation fadeOut = new AlphaAnimation( 1.0f , 0.0f ) ;

        TextView textView = findViewById(R.id.textViewPointsAdded);
        textView.startAnimation(fadeIn);
        textView.startAnimation(fadeOut);
        fadeIn.setDuration(10);
        fadeIn.setFillAfter(true);
        fadeOut.setDuration(100);
        fadeOut.setFillAfter(true);
        fadeOut.setStartOffset(1000+fadeIn.getStartOffset());
    }

    private void wrongOrRightText() {
        AlphaAnimation fadeOut = new AlphaAnimation( 1.0f , 0.0f ) ;

        TextView textView = findViewById(R.id.textViewPointsAdded);
        textView.startAnimation(fadeOut);
        fadeOut.setDuration(0);
        fadeOut.setFillAfter(true);
    }
}