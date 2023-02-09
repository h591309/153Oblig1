package com.example.oblig1;


import android.graphics.BitmapFactory;
import android.util.Log;

/**
 *
 * Singleton for access to the EntriesArrayList throughout the application
 */
public class EntriesSingleton {

    private static volatile EntriesSingleton INSTANCE = null;
    private static volatile EntriesArrayList entries = null;
    private static boolean exampleExist = false;

    private EntriesSingleton() {
        entries = new EntriesArrayList();
    }
    public static EntriesSingleton getInstance() {
        if(INSTANCE == null)
        synchronized (EntriesSingleton.class) {
            if(INSTANCE == null) {
                INSTANCE = new EntriesSingleton();
            }
        }
        return INSTANCE;
    }

    public EntriesArrayList getEntries() {
        return entries;
    }

    public boolean exampleDataExist() {
        return exampleExist;
    }

    public void setExampleDataExist() {
        exampleExist = true;
    }

    public void addExampleData(Entry e) {
        entries.addEntry(e);
    }

    public void removeEntry(int id) {
        entries.removeEntry(id);
    }

    public boolean doesEntryExist(int id) {
        if(entries.getEntry(id) != null)
            return true;

        return false;
    }
}
