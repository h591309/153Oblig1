/**
 *
 * Activity for showing entries in a list
 */

package com.example.oblig1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.oblig1.databinding.ActivityDatabaseBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 *
 * Activity for displaying list of quiz-questions (entries).
 */
public class DatabaseActivity extends MainActivity implements View.OnClickListener {

    private ActivityDatabaseBinding binding;
    private AppBarConfiguration appBarConfiguration;

    private EntriesSingleton db = EntriesSingleton.getInstance();
    private ViewBaseAdapter viewBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db.getEntries().sortAZ();
        binding = ActivityDatabaseBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_database);
        ListView listView;
        listView = (ListView) findViewById(R.id.listview_content);


        viewBaseAdapter = new ViewBaseAdapter(this, db);
        listView.setAdapter(viewBaseAdapter);

        FloatingActionButton fab = findViewById(R.id.databaseFab);
        fab.setOnClickListener(this);
        Button btnSort = findViewById(R.id.sortButton);
        btnSort.setOnClickListener(this);


        //setSupportActionBar(binding.toolbar);
    }

   @Override
   protected void onStart() {
       super.onStart();
       Button sortButton = findViewById(R.id.sortButton);
       sortButton.setText(db.getEntries().getSortedAZ() ? "Sorted A-Z" : "Sorted Z-A");
       viewBaseAdapter.notifyDataSetChanged();
   }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.databaseFab:
                Intent intent = new Intent(binding.getRoot().getContext(), AddNewEntryActivity.class);
                startActivity(intent);
                break;
            case R.id.sortButton:
                Log.d("BUTTON PRESSED", "Sort: AZ og ZA");
                Button btn = findViewById(R.id.sortButton);
                EntriesArrayList entries = db.getEntries();

                if (entries.getSortedAZ()) {
                    entries.sortZA();
                } else {
                    entries.sortAZ();
                }

                btn.setText(entries.getSortedAZ() ? "Sorted A-Z" : "Sorted Z-A");
                viewBaseAdapter.notifyDataSetChanged();
                break;
        }
    }
}
