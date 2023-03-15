package com.example.oblig1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.oblig1.databinding.ActivityDatabaseBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


/**
 *
 * Activity for displaying list of quiz-questions (entries).
 */
public class DatabaseActivity extends MainActivity implements View.OnClickListener {

    private ActivityDatabaseBinding binding;

    private ViewBaseAdapter viewBaseAdapter;

    private EntriesAccessObject entriesAccessObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        entriesAccessObject = new EntriesAccessObject(getApplication());
        binding = ActivityDatabaseBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_database);
        ListView listView;
        listView = (ListView) findViewById(R.id.listview_content);

        LiveData<List<Entry>> allEntries = entriesAccessObject.getAllEntries();
        viewBaseAdapter = new ViewBaseAdapter(this.getApplication(), getBaseContext(), entriesAccessObject);
        allEntries.observe(this, new Observer<List<Entry>>() {
            @Override
            public void onChanged(List<Entry> entries) {
                listView.setAdapter(viewBaseAdapter);
                viewBaseAdapter.notifyDataSetChanged();
            }
        });


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
       sortButton.setText(entriesAccessObject.getSortedAZ() ? "Sorted A-Z" : "Sorted Z-A");
       viewBaseAdapter.notifyDataSetChanged();
   }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.databaseFab:
                Intent intent = new Intent(binding.getRoot().getContext(), AddNewEntryActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.sortButton:
                Log.d("BUTTON PRESSED", "Sort: AZ og ZA");
                Button btn = findViewById(R.id.sortButton);


                if (entriesAccessObject.getSortedAZ()) {
                    entriesAccessObject.sortZA();
                } else {
                    entriesAccessObject.sortAZ();
                }

                btn.setText(entriesAccessObject.getSortedAZ() ? "Sorted A-Z" : "Sorted Z-A");
                viewBaseAdapter.notifyDataSetChanged();
                break;
        }
    }
}
