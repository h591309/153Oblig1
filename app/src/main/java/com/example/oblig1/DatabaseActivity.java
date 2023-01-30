package com.example.oblig1;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.oblig1.databinding.ActivityDatabaseBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.zip.Inflater;

public class DatabaseActivity extends MainActivity {

    private ActivityDatabaseBinding binding;
    private AppBarConfiguration appBarConfiguration;

    private final EntryDatabase db = new EntryDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db.addExampleData();
        binding = ActivityDatabaseBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_database);
        ListView listView;
        listView = (ListView) findViewById(R.id.listview_content);

        ViewBaseAdapter viewBaseAdapter = new ViewBaseAdapter(getApplicationContext(), db.getEntries());
        listView.setAdapter(viewBaseAdapter);
        setSupportActionBar(binding.toolbar);

        binding.databaseFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Database", "onClick: Add entry");
            }
        });


    }
}
