package com.example.oblig1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SwitchCompat;
import androidx.navigation.ui.AppBarConfiguration;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.oblig1.databinding.ActivityMainBinding;

/**
 *
 * Main activity for the application. This is the first Activity to be run when application starts.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private ConverterHelper converter = new ConverterHelper();
    private EntriesAccessObject entriesAccessObject;

    private boolean hardMode;
    SwitchCompat aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        entriesAccessObject = new EntriesAccessObject(getApplication());
        setContentView(R.layout.activity_main);
        Button btnDB = findViewById(R.id.button_database);
        Button btnQuiz = findViewById(R.id.button_quiz);
        btnDB.setOnClickListener(this);
        btnQuiz.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_database:
                Intent databaseIntent = new Intent(this, DatabaseActivity.class);
                startActivity(databaseIntent);
                break;
            case R.id.button_quiz:
                aSwitch = findViewById(R.id.hard_mode_switch);
                if(aSwitch != null) {
                    hardMode = aSwitch.isChecked();
                }
                Intent quizIntent = new Intent(this, QuizActivity.class);
                quizIntent.putExtra("hard_mode", hardMode);
                startActivity(quizIntent);
                break;
        }
    }
}