package com.example.oblig1;

import java.util.ArrayList;
import java.util.List;

public class EntriesArrayList {

    private List<Entry> entries = null;
    private int lastIndex;

    public EntriesArrayList() {
        this.entries = new ArrayList<>();
        lastIndex = 0;
    }

    public EntriesArrayList(List<Entry> entries) {
        this.entries = entries;
    }

    public void addEntry(Entry entry) {
        entry.setId(lastIndex);
        lastIndex++;
        entries.add(entry);
    }

    public void removeEntry(int id) {
        for(int i = 0; i < entries.size(); i++) {
            if(entries.get(i).getId() == id) {
                entries.remove(id);
            }
        }
        lastIndex--;
    }

    public Entry getEntry(int id) {
        return entries.get(id);
    }

    public int size() {
        return entries.size();
    }

}
