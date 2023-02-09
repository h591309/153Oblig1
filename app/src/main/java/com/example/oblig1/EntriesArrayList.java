/**
 *
 *
 * Used for storing entries in a array list.
 */

package com.example.oblig1;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * Class for creating an array-list that can contain Entry objects.
 */
public class EntriesArrayList {

    private static List<Entry> entries = null;
    private static int lastIndex;
    private boolean isSortedAZ = false;

    /**
     * Creates empty EntriesArrayList
     *
     */
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

    public void sortAZ() {
        Log.d("SORTIN AZ", "sortAZ: ");
        if(entries.isEmpty()) {
            return;
        }
        isSortedAZ = true;
        entries.sort(new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
            }
        });
    }

    public void sortZA() {
        Log.d("SORTIN ZA", "sortZA: ");
        if(entries.isEmpty()) {
            return;
        }
        isSortedAZ = false;
        entries.sort(new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return o2.getName().toLowerCase().compareTo(o1.getName().toLowerCase());
            }
        });
    }


    public boolean getSortedAZ() {
        return isSortedAZ;
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
