package com.example.vibrantzz3.snipsearchadmin;

/**
 * Created by Vibrantzz3 on 2/15/2018.
 */

public class Offers {

    private String UID,ID,Name,Location,Thumbnail,UDate, UTime, Request;


    public Offers(String uid, String id, String request)
    {

        ID=id;
        UID=uid;
        Request=request;
    }

    public String getRequest() {
        return Request;
    }

    public void setRequest(String request) {
        Request = request;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getUDate() {
        return UDate;
    }

    public void setUDate(String UDate) {
        this.UDate = UDate;
    }

    public String getUTime() {
        return UTime;
    }

    public void setUTime(String UTime) {
        this.UTime = UTime;
    }
}
