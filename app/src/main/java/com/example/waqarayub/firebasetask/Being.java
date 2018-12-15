package com.example.waqarayub.firebasetask;

public class Being
{
    String ID;
    String beingname;
    String Genre;

    public Being(){}

    public Being(String ID, String beingname, String genre)
    {
        this.ID = ID;
        this.beingname = beingname;
        this.Genre = genre;
    }

    public String getBeingId() {
        return ID;
    }

    public String getBeingname() {
        return beingname;
    }

    public String getBeingGenre() {
        return Genre;
    }
}
