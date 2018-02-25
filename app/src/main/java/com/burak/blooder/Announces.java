package com.burak.blooder;


/**
 * Created by burak on 4/26/2017.
 */

public class Announces {

    String requesterId;
    String requestedBlood;

    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    public String getRequestedBlood() {
        return requestedBlood;
    }

    public void setRequestedBlood(String requestedBlood) {
        this.requestedBlood = requestedBlood;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRequesterLocX() {
        return requesterLocX;
    }

    public void setRequesterLocX(double requesterLocX) {
        this.requesterLocX = requesterLocX;
    }

    public double getRequesterLocY() {
        return requesterLocY;
    }

    public void setRequesterLocY(double requesterLocY) {
        this.requesterLocY = requesterLocY;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String description;
    double requesterLocX;
    double requesterLocY;
    String date;

    public Announces() {

    }


    public Announces(String requesterId, String requestedBlood, String description, double requesterLocX, double requesterLocY, String date) {
        this.requesterId = requesterId;
        this.requestedBlood = requestedBlood;
        this.description = description;
        this.requesterLocX = requesterLocX;
        this.requesterLocY = requesterLocY;
        this.date = date;
    }


}
