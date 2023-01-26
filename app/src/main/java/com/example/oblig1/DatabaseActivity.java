package com.example.oblig1;

import android.os.Bundle;

import com.example.oblig1.databinding.ActivityDatabaseBinding;

public class DatabaseActivity extends MainActivity {

    private ActivityDatabaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDatabaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
