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

    private static final int timerStartsFrom = 30;

    private int numberOfCorrectAnswers = 0;

    private TextView textViewTimer;

    MyTimer timer;
    private EntriesSingleton db = EntriesSingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        numberOfQuestions = 0;
        hardMode = (boolean) getIntent().getBooleanExtra("hard_mode", false);

        textViewTimer = findViewById(R.id.textViewTimer);
        timer = new MyTimer(this, timerStartsFrom, textViewTimer);


        wrongOrRightText();
        Log.d("CORRECT BUTTON", "onCreate: " + correctButton);
    }

    @Override
    protected void onStart() {
        super.onStart();
        timer.setTimeLeft(timerStartsFrom);
        quizQuest();
        Log.d("HARMODE", "onStart: " + (hardMode ? "on" : "off"));
    }

    private void startTimer() {



    }

    private void quizQuest() {
        timer.setTimeLeft(timerStartsFrom);
        if(hardMode) {
            if(!timer.isRunning()) {
                timer.start();
            }
        } else {
            textViewTimer  = findViewById(R.id.textViewTimer);
            textViewTimer.setAlpha(0);
        }

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
                break;
            case 2:
                btn2.setText(question.getName());
                btn1.setText(wrongName1);
                btn3.setText(wrongName2);
                break;
            case 3:
                btn3.setText(question.getName());
                btn2.setText(wrongName1);
                btn1.setText(wrongName2);
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
        timer.setTimeLeft(timerStartsFrom);
        boolean correctAnswer = false;
        int pointsToAdd = 0;
        boolean timeOut = false;
        TextView textViewPointsAdded = findViewById(R.id.textViewPointsAdded);
        switch(v.getId()){
            case R.id.quizBtn1:
                if(correctButton == 1)
                    correctAnswer = true;
                break;
            case R.id.quizBtn2:
                if(correctButton == 2)
                    correctAnswer = true;
                break;
            case R.id.quizBtn3:
                if(correctButton == 3)
                    correctAnswer = true;
                break;
        }

        if(timer.getTimeLeft() < 0) {
            correctAnswer = false;
            timeOut = true;
            textViewPointsAdded.setTextColor(getResources().getColor(R.color.red, getTheme()));
            textViewPointsAdded.setText(R.string.tooSlow);
        }
        if(correctAnswer) {
            pointsToAdd = 10;
            numberOfCorrectAnswers++;
        } else {
            pointsToAdd = -5;
        }

        if(!timeOut) {
            if(pointsToAdd > 0) {
                textViewPointsAdded.setTextColor(getResources().getColor(R.color.correct_green, getTheme()));
                textViewPointsAdded.setText(R.string.right_answer);
            } else {
                textViewPointsAdded.setTextColor(getResources().getColor(R.color.red, getTheme()));
                textViewPointsAdded.setText(R.string.wrong_answer);
            }
        }

        score += pointsToAdd;

        textViewAnimation();

        TextView text = (TextView) findViewById(R.id.textViewQuiz);
        text.setText("Points: " + score);
        TextView numOfCorrectTextView = findViewById(R.id.textViewNumberOfCorrect);
        numOfCorrectTextView.setTextColor(getResources().getColor(R.color.black, getTheme()));
        numOfCorrectTextView.setText(getResources().getString(R.string.score) + " " + numberOfCorrectAnswers + " / " + (numberOfQuestions));

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