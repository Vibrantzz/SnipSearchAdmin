package com.example.vibrantzz3.snipsearchadmin;

/**
 * Created by Vibrantzz3 on 3/1/2018.
 */

public class Menu {

    public String ID, Image;

    public Menu(String id, String image) {
        ID = id;
        Image = image;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
