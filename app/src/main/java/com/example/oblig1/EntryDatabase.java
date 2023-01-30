package com.example.oblig1;


import android.util.Log;

import java.util.ArrayList;

public class EntryDatabase {

    private EntriesArrayList entries;

    public EntryDatabase() {
        entries = new EntriesArrayList();
    }

    public void addExampleData() {
        Entry e1 = new Entry("Cat 1", R.drawable.cat1);
        Entry e2 = new Entry("Cat 2", R.drawable.cat2);
        Entry e3 = new Entry("Not a cat", R.drawable.cat3);
        Entry e4 = new Entry("Cat 432", R.drawable.cat4);
        Entry e5 = new Entry("Another one", R.drawable.cat5);
        entries.addEntry(e1);
        entries.addEntry(e2);
        entries.addEntry(e3);
        entries.addEntry(e4);
        entries.addEntry(e5);
        Log.d("addExampleData", "addExampleData: " + entries.getEntry(1));

    }

    public EntriesArrayList getEntries() {
        return this.entries;
    }

    public void addEntry(Entry entry) {
        this.entries.addEntry(entry);
    }

    public void removeEntry(int id) {
        this.entries.removeEntry(id);
    }

    public Entry getEntry(int id) {
        return this.entries.getEntry(id);
    }
    public int size() {
        return entries.size();
    }
}
