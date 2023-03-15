package com.example.oblig1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class QuizViewModel extends ViewModel {
    private EntriesAccessObject db;

    private int score;
    private Entry correct;
    private String wrong1;

    public QuizViewModel(Entry correct, String wrong1, String wrong2) {
        super();
        this.correct = correct;
        this.wrong1 = wrong1;
        this.wrong2 = wrong2;
    }

    public EntriesAccessObject getDb() {
        return db;
    }

    public void setDb(EntriesAccessObject db) {
        this.db = db;
    }

    public Entry getCorrect() {
        return correct;
    }

    public void setCorrect(Entry correct) {
        this.correct = correct;
    }

    public String getWrong1() {
        return wrong1;
    }

    public void setWrong1(String wrong1) {
        this.wrong1 = wrong1;
    }

    public String getWrong2() {
        return wrong2;
    }

    public void setWrong2(String wrong2) {
        this.wrong2 = wrong2;
    }

    public LiveData<List<Entry>> getAllEntries() {
        return allEntries;
    }

    public void setAllEntries(LiveData<List<Entry>> allEntries) {
        this.allEntries = allEntries;
    }

    private String wrong2;
    private LiveData<List<Entry>> allEntries;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
