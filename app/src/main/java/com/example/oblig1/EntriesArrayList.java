/**
 *
 *
 * Used for storing entries in a array list.
 */

package com.example.oblig1;

import java.util.ArrayList;
import java.util.List;

public class EntriesArrayList {

    private static List<Entry> entries = null;
    private static int lastIndex;

    public EntriesArrayList() {
        this.entries = new ArrayList<>();
        lastIndex = 0;
    }

    public boolean isEmpty() {
        if(lastIndex == 0)
            return true;

        return false;
    }

    public EntriesArrayList getEntries() {
        return new EntriesArrayList(entries);
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
        entries.remove(id);
        lastIndex--;
    }

    public Entry getEntry(int id) {
        return entries.get(id);
    }

    public int size() {
        return entries.size();
    }

}
