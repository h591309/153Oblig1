package com.example.oblig1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface EntryDao {
    @Query("SELECT * FROM entry")
    LiveData<List<Entry>> getEntries();

    @Query("SELECT * FROM entry WHERE id IN (:entryIds)")
    LiveData<List<Entry>> loadAllByIds(int[] entryIds);


    @Update
    public void updateUsers(Entry... entries);

    @Insert
    void insertAll(Entry... Entries);

    @Insert
    void insert(Entry e);

    @Delete
    void delete(Entry entry);

    @Query("DELETE FROM Entry WHERE id = :entryId")
    void deleteById(int entryId);

    @Query("DELETE FROM Entry")
    void deleteAll();


    @Query("SELECT * FROM Entry ORDER BY name ASC")
    LiveData<List<Entry>> getNamesAZ();

    @Query("SELECT * FROM Entry ORDER BY name DESC")
    LiveData<List<Entry>> getNamesZA();
}