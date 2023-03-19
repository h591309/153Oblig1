package com.example.oblig1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static java.util.EnumSet.allOf;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.oblig1.Entry;
import com.example.oblig1.QuizActivity;
import com.example.oblig1.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class QuizActivityTest {

    @Rule
    public ActivityScenarioRule<QuizActivity> activityScenarioRule =
            new ActivityScenarioRule<>(QuizActivity.class);


    @Test
    public void testScoreIncreasesOnCorrectAnswer() {

        activityScenarioRule.getScenario().onActivity(activity -> {
            TextView scoreView = activity.findViewById(R.id.textViewQuiz);
            String scoreText = scoreView.getText().toString();
            String numbersOnly = scoreText.substring(scoreText.indexOf(" ") + 1);
            numbersOnly.trim();
            int initialScore = Integer.parseInt(numbersOnly);

            Entry correctAnswer = activity.getQuestion();
            assertNotNull(correctAnswer);
            Button btn1 = activity.findViewById(R.id.quizBtn1);
            Button btn2 = activity.findViewById(R.id.quizBtn2);
            Button btn3 = activity.findViewById(R.id.quizBtn3);

            Button correctButton = null;
            if (correctAnswer.getName().equals(btn1.getText().toString())) {
                correctButton = btn1;
            } else if (correctAnswer.getName().equals(btn2.getText().toString())) {
                correctButton = btn2;
            } else if (correctAnswer.getName().equals(btn3.getText().toString())) {
                correctButton = btn3;
            }
            assertNotNull(correctButton);

            correctButton.performClick();
            TextView updatedScoreView = activity.findViewById(R.id.textViewQuiz);
            String updatedScoreText = updatedScoreView.getText().toString();
            String numbersOnlyUpdated = updatedScoreText.substring(scoreText.indexOf(" ") + 1);
            int updatedScore = Integer.parseInt(numbersOnlyUpdated);
            assertEquals(initialScore + 10, updatedScore);
        });
    }

    @Test
    public void testScoreDecreaseOnWrongAnswer() {
        activityScenarioRule.getScenario().onActivity(activity -> {

            TextView scoreView = activity.findViewById(R.id.textViewQuiz);
            String scoreText = scoreView.getText().toString();
            String numbersOnly = scoreText.substring(scoreText.indexOf(" ") + 1);
            int initialScore = Integer.parseInt(numbersOnly);

            Entry correctAnswer = activity.getQuestion();
            assertNotNull(correctAnswer);

            Button btn1 = activity.findViewById(R.id.quizBtn1);
            Button btn2 = activity.findViewById(R.id.quizBtn2);
            Button btn3 = activity.findViewById(R.id.quizBtn3);

            Button wrongButton = null;
            if (!correctAnswer.getName().equals(btn1.getText().toString())) {
                wrongButton = btn1;
            } else if (!correctAnswer.getName().equals(btn2.getText().toString())) {
                wrongButton = btn2;
            } else if (!correctAnswer.getName().equals(btn3.getText().toString())) {
                wrongButton = btn3;
            }
            assertNotNull(wrongButton);
            wrongButton.performClick();

            TextView updatedScoreView = activity.findViewById(R.id.textViewQuiz);
            String updatedScoreText = updatedScoreView.getText().toString();
            String numbersOnlyUpdated = updatedScoreText.substring(scoreText.indexOf(" ") + 1);
            int updatedScore = Integer.parseInt(numbersOnlyUpdated);


            assertEquals(initialScore -5, updatedScore);
        });
    }

}