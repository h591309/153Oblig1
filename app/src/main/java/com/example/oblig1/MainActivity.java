package com.example.oblig1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SwitchCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.Menu;
import android.view.MenuItem;
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
    private EntriesSingleton db = EntriesSingleton.getInstance();
    SwitchCompat aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
            Adds example-data
            TODO: To be removed when no longer needed.
         */
        if(!db.exampleDataExist()) {
            Entry e = new Entry("Kitten with blue eyes", createBitmapFromDrawable(R.drawable.cat1));
            db.addExampleData(e);
            e = new Entry("Black cat with yellow eyes", createBitmapFromDrawable(R.drawable.cat2));
            db.addExampleData(e);
            e = new Entry("Not a cat", createBitmapFromDrawable(R.drawable.cat3));
            db.addExampleData(e);
            e = new Entry("Striped cat", createBitmapFromDrawable(R.drawable.cat4));
            db.addExampleData(e);
            e = new Entry("White belly cat", createBitmapFromDrawable(R.drawable.cat5));
            db.addExampleData(e);
            db.setExampleDataExist();
        }
        setContentView(R.layout.activity_main);

        aSwitch = (SwitchCompat) findViewById(R.id.hard_mode_switch);
        Button btnDB = findViewById(R.id.button_database);
        Button btnQuiz = findViewById(R.id.button_quiz);

        btnDB.setOnClickListener(this);
        btnQuiz.setOnClickListener(this);
    }

    /**
     * Creates bitmap from given drawable.
     * @param drawableId
     * @return Bitmap
     */
    private Bitmap createBitmapFromDrawable(int drawableId) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableId);
        return bitmap;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_database:
                Intent databaseIntent = new Intent(this, DatabaseActivity.class);
                startActivity(databaseIntent);
                break;
            case R.id.button_quiz:

                boolean hardMode = false;
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