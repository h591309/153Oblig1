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
import android.widget.ListView;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.oblig1.databinding.ActivityDatabaseBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class DatabaseActivity extends MainActivity implements View.OnClickListener {

    private ActivityDatabaseBinding binding;
    private AppBarConfiguration appBarConfiguration;

    private EntriesSingleton db = EntriesSingleton.getInstance();
    private ViewBaseAdapter viewBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDatabaseBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_database);
        ListView listView;
        listView = (ListView) findViewById(R.id.listview_content);


        viewBaseAdapter = new ViewBaseAdapter(this, db);
        listView.setAdapter(viewBaseAdapter);

        FloatingActionButton fab = findViewById(R.id.databaseFab);
        fab.setOnClickListener(this);

        //setSupportActionBar(binding.toolbar);
    }

   @Override
   protected void onStart() {
       super.onStart();
       viewBaseAdapter.notifyDataSetChanged();
   }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.databaseFab:
                Intent intent = new Intent(binding.getRoot().getContext(), AddNewEntryActivity.class);
                startActivity(intent);
                break;
        }
    }
}
