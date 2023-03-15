package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oblig1.databinding.ActivityQuizBinding;

import java.util.List;


/**
 *
 * Activity for the quiz.
 */
public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityQuizBinding binding;
    private int score = 0;
    private int correctButton;
    private int numberOfQuestions;
    private boolean hardMode = false;
    private static final int timerStartsFrom = 30;
    private int numberOfCorrectAnswers = 0;
    private TextView textViewTimer;

    private MyTimer timer;
    private EntriesAccessObject entriesAccessObject;
    private LiveData<List<Entry>> allEntries;
    private QuizViewModel qvm;

    private ConverterHelper converter = new ConverterHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        numberOfQuestions = 0;
        hardMode = (boolean) getIntent().getBooleanExtra("hard_mode", false);
        textViewTimer = findViewById(R.id.textViewTimer);
        timer = new MyTimer(this, timerStartsFrom, textViewTimer);
        entriesAccessObject = new EntriesAccessObject(getApplication());
        wrongOrRightText();
        Log.d("CORRECT BUTTON", "onCreate: " + correctButton);
    }

    @Override
    protected void onStart() {
        super.onStart();
        allEntries = entriesAccessObject.getAllEntries();
        allEntries.observe(this, new Observer<List<Entry>>() {
            @Override
            public void onChanged(List<Entry> entries) {
                quizQuest();
            }
        });
        timer.setTimeLeft(timerStartsFrom);
        Log.d("HARMODE", "onStart: " + (hardMode ? "on" : "off"));

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);
    }

    private void startTimer() {



    }

    /**
     * Displays a quiz-squestion.
     *
     * Shows one image and picks one button to have the correct name.
     * Picks two other random names from the other entries to display on the two other buttons.
     * Also binds onClickListener() to these buttons.
     */
    private void quizQuest() {
        timer.setTimeLeft(timerStartsFrom);
        numberOfQuestions++;

        //Check to see if hardmode is enabled.
        if (hardMode) {
            //Starts timer-thread as long as its not running already.
            if (!timer.isRunning()) {
                timer.start();
            }
            TextView modeTxt = (TextView) findViewById(R.id.textViewMode);
            modeTxt.setText("Hard mode");
        } else {
            //Sets timer text to be invisible.
            textViewTimer = findViewById(R.id.textViewTimer);
            textViewTimer.setAlpha(0);
        }

        //Picks one of the buttons to be the correct one.
        correctButton = (int) Math.round((Math.random() * 2) + 1);

        Entry question = pickRandomQuizQuestion();

        //Picks to random wrong answers.
        String wrongName1 = pickRandomQuizQuestion().getName();
        String wrongName2 = pickRandomQuizQuestion().getName();

        boolean done = false;

        //Makes sure the two given wrong answers are not equal to each other or the correct answer.
        while (!done) {
            if (wrongName1 == question.getName()) {
                wrongName1 = pickRandomQuizQuestion().getName();
            } else if (wrongName2 == question.getName()) {
                wrongName2 = pickRandomQuizQuestion().getName();
            } else if (wrongName1 == wrongName2) {
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

        //Sets correct text for the buttons
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
        //Sets the correct image for quiz-question
        ImageView img = findViewById(R.id.imageViewQuiz);
        img.setImageBitmap(converter.ByteArrayToBitmap(question.getImg()));

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        //Sets the score text
        TextView text = (TextView) findViewById(R.id.textViewQuiz);
        text.setText("Score: " + score);
    }

    /**
     * Picks a random quiz-question.
     * @return Entry
     */

    private Entry pickRandomQuizQuestion() {
        int randomNumber = (int) Math.round(Math.random() * (allEntries.getValue().size()-1));
        return allEntries.getValue().get(randomNumber);
    }

    @Override
    public void onClick(View v) {
        boolean correctAnswer = false;
        int pointsToAdd = 0;
        boolean timeOut = false;
        TextView textViewPointsAdded = findViewById(R.id.textViewPointsAdded);
        Boolean done = false;

        //Checks for what button is pressed.
        switch(v.getId()){
            case R.id.backButton:
                done = true;
                Intent quizIntent = new Intent(this, MainActivity.class);
                //quizIntent.putExtra("hard_mode", hardMode);
                startActivity(quizIntent);
                finish();
                break;
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
        if(done)
            return;
        //Check to see if there is time left on hardmode
        if(timer.getTimeLeft() < 0) {
            correctAnswer = false;
            timeOut = true;
        }

        //Check to see if correct answer was picked.
        if(correctAnswer) {
            pointsToAdd = 10;
            numberOfCorrectAnswers++;
        } else {
            pointsToAdd = -5;
        }

        //Checks if time ran out
        if(!timeOut) {
            //Updates display for wrong or right answer.
            if(pointsToAdd > 0) {
                textViewPointsAdded.setTextColor(getResources().getColor(R.color.correct_green, getTheme()));
                textViewPointsAdded.setText(R.string.right_answer);
            } else {
                textViewPointsAdded.setTextColor(getResources().getColor(R.color.red, getTheme()));
                textViewPointsAdded.setText(R.string.wrong_answer);
            }
        } else {
            textViewPointsAdded.setTextColor(getResources().getColor(R.color.red, getTheme()));
            textViewPointsAdded.setText(R.string.tooSlow);
        }

        //Adds correct amount of points to the score.
        score += pointsToAdd;

        textViewAnimation();


        TextView text = (TextView) findViewById(R.id.textViewQuiz);
        text.setText("Points: " + score);
        TextView numOfCorrectTextView = findViewById(R.id.textViewNumberOfCorrect);
        numOfCorrectTextView.setTextColor(getResources().getColor(R.color.black, getTheme()));
        numOfCorrectTextView.setText(getResources().getString(R.string.score) + " " + numberOfCorrectAnswers + " / " + (numberOfQuestions));

        //Shows a new quiz question.
        quizQuest();

    }

    /**
     * Creates bitmap from drawable.
     * @param drawableId
     * @return Bitmap
     */
    private Bitmap createBitmapFromDrawable(int drawableId) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableId);
        return bitmap;
    }

    /**
     * Creates 3 Entries for the database.
     * 3 is minimum amount of entries for this quiz.
     */
    /*
    private void createBackupEntries() {
        Entry e = new Entry("Kitten with blue eyes", converter.BitmapToByteArray(createBitmapFromDrawable(R.drawable.cat1)));
        quizViewModel.addExampleData(e);
        e = new Entry("Black cat with yellow eyes", converter.BitmapToByteArray(createBitmapFromDrawable(R.drawable.cat2)));
        quizViewModel.addExampleData(e);
        e = new Entry("Not a cat", converter.BitmapToByteArray(createBitmapFromDrawable(R.drawable.cat3)));
        quizViewModel.addExampleData(e);
    }

     */

    /**
     * Animation for textview showing wrong/right answer.
     */
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

    /**
     *
     * Animation to initially hide the textview showing wrong/right answer.
     *
     */
    private void wrongOrRightText() {
        AlphaAnimation fadeOut = new AlphaAnimation( 1.0f , 0.0f ) ;

        TextView textView = findViewById(R.id.textViewPointsAdded);
        textView.startAnimation(fadeOut);
        fadeOut.setDuration(0);
        fadeOut.setFillAfter(true);
    }
}