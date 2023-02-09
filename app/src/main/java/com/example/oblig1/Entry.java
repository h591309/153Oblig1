/**
 *
 * For storing quiz entries
 */

package com.example.oblig1;

import android.graphics.Bitmap;

/**
 *
 * Entry object class.
 */
public class Entry {

    private int id;
    private Bitmap img;
    private String name;
    private String wrongName1;
    private String wrongName2;
    public Entry() {

        this.name = "No name specified";
    }

    /**
     *
     * Creates an Entry-object.
     * @param name
     * @param img
     */
    public Entry(String name, Bitmap img) {
        this.img = img;
        this.name = name;
    }

    /**
     * Returns the id for Entry
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id for Entry.
     * @param id
     */
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
