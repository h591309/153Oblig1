package com.example.oblig1;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.oblig1.Entry;
import com.example.oblig1.EntryDao;
import com.example.oblig1.QuestionDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class QuestionDatabaseTest {

    private EntryDao dao;
    private QuestionDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, QuestionDatabase.class).build();
        dao = db.entryDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void insertEntryAndGetEntry() {
        Entry entry = new Entry();
        int entryId = 666;
        entry.setId(entryId);
        dao.insert(entry);
        Entry byId = dao.findById(entryId);
        assertNotNull(byId);
        assertTrue(byId.equals(entry));
    }

    @Test
    public void insertEntryAndDeleteEntry() {
        Entry entry = new Entry();
        int entryId = 666;
        entry.setId(entryId);
        dao.insert(entry);
        Entry byId = dao.findById(entryId);
        dao.delete(byId);
        byId = dao.findById(entryId);
        assertNull(byId);
    }
}