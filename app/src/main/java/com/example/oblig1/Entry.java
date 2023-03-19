/**
 *
 * For storing quiz entries
 */

package com.example.oblig1;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 *
 * Entry object class.
 */
@Entity
public class Entry {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @ColumnInfo(name = "img")
    private byte[] img;
    @ColumnInfo(name = "name")
    private String name;

    public Entry() {
        this.name = "No name specified";
    }

    /**
     *
     * Creates an Entry-object.
     * @param name
     * @param img
     */
    public Entry(String name, byte[] img) {
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

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    boolean equals(Entry entry) {
        if(this.id == entry.getId()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        ConverterHelper dbHelper = new ConverterHelper();
        return "Entry{" +
                "id=" + id +
                ", img='" + dbHelper.ByteArrayToBitmap(img) + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
