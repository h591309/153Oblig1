package com.example.oblig1;


import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * Singleton for access to the EntriesArrayList throughout the application
 */
public class EntriesRepository {

    private EntryDao entryDao;
    private LiveData<List<Entry>> allEntries;
    private static boolean exampleExist = false;

    private boolean isSortedAZ;

    EntriesRepository(Application application) {
        QuestionDatabase db = QuestionDatabase.getDatabase(application);
        entryDao = db.entryDao();
        allEntries = entryDao.getNamesAZ();
        isSortedAZ = true;
    }

    public LiveData<List<Entry>> getAllEntries() {
        return allEntries;
    }

    public boolean exampleDataExist() {
        return exampleExist;
    }

    public void setExampleDataExist() {
        exampleExist = true;
    }

    public void addExampleData(Entry e) {
        //TODO Implement
    }

    /**
     * insert Entry in to database.
     * @param entry
     */
    void insert(Entry entry) {
        QuestionDatabase.databaseWriteExecutor.execute(() -> {
            entryDao.insert(entry);
        });
    }

    /**
     * Deletes Entry from database by id.
     * @param id
     */
    public void delete(int id) {
        QuestionDatabase.databaseWriteExecutor.execute(() -> {
            entryDao.deleteById(id);
        });
    }

    /**
     * Checks if the list is sorted A-Z or not
     * @return true if data is sorted A-Z
     */
    public boolean getSortedAZ() {
        return isSortedAZ;
    }

    /**
     * Setter for isSortedAZ variable.
     * @param sortedAZ
     */
    public void setSortedAZ(boolean sortedAZ) {
        isSortedAZ = sortedAZ;
    }
}
