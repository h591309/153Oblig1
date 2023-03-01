package com.example.oblig1;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.Comparator;
import java.util.List;

public class QuizViewModel extends AndroidViewModel {
    private EntriesRepository repo;

    private final LiveData<List<Entry>> allEntries;

    public QuizViewModel (Application application) {
        super(application);
        repo = new EntriesRepository(application);
        allEntries = repo.getAllEntries();

    }

    public LiveData<List<Entry>> getAllEntries() {
        return allEntries;
    }

    public void insert(Entry entry) { repo.insert(entry); }

    public void delete(int id) {
        repo.delete(id);
    }

    public int getRepoSize() {
        return allEntries.getValue().size();
    }

    public boolean getSortedAZ() {
        return repo.getSortedAZ();
    }

    public void sortAZ() {
        Log.d("SORTING AZ", "sortAZ: ");
        if(allEntries.getValue().isEmpty()) {
            return;
        }
        repo.setSortedAZ(true);
        allEntries.getValue().sort(new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
            }
        });
    }

    public void sortZA() {
        Log.d("SORTING ZA", "sortZA: ");
        if(allEntries.getValue().isEmpty()) {
            return;
        }
        repo.setSortedAZ(false);
        allEntries.getValue().sort(new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return o2.getName().toLowerCase().compareTo(o1.getName().toLowerCase());
            }
        });
    }
    public boolean findEntryById(int id) {
        if(allEntries.getValue().isEmpty()) {
            return false;
        }
        //TODO fix
        return false;
    }
}
