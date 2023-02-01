/**
 *
 * For storing quiz entries
 */

package com.example.oblig1;

public class Entry {

    private int id;
    private int img;
    private String name;
    private String wrongName1;
    private String wrongName2;
    public Entry() {
        this.img = R.drawable.cat1;
        this.name = "No name specified";
    }

    public Entry(String name, int img) {
        this.img = img;
        this.name = name;
    }

    public Entry(String name, String wrongName1, String wrongName2, int img) {
        this.img = img;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
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