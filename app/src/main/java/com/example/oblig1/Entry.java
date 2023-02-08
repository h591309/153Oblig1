/**
 *
 * For storing quiz entries
 */

package com.example.oblig1;

import android.graphics.Bitmap;

public class Entry {

    private int id;
    private Bitmap img;
    private String name;
    private String wrongName1;
    private String wrongName2;
    public Entry() {

        this.name = "No name specified";
    }

    public Entry(String name, Bitmap img) {
        this.img = img;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
