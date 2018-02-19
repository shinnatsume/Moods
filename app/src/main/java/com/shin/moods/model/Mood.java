package com.shin.moods.model;

import java.util.Date;

/**
 * Created by shin on 25/01/2018.
 */

public class Mood {
    //todo creer variable commentText pour enregistrer le commentaire et les getter et setter du textcomment et du l'id
    private int icon;
    private int background;
    private int comment;
    private int historic;
    private int idMood;
    private String commentText;
    private Date mDate;



    public Mood(int icon, int background, int comment, int historic, int idPosition,String commentText,Date mdate) {
        this.icon = icon;
        this.background = background;
        this.comment = comment;
        this.historic = historic;
        this.idMood = idPosition;
        this.commentText = commentText;
        this.mDate = mdate;
    }

    public int getIdMood() {
        return idMood;
    }

    public void setIdMood(int idMood) {
        this.idMood = idMood;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date dateClick) {
        this.mDate = dateClick;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }


    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getHistoric() {
        return historic;
    }

    public void setHistoric(int historic) {
        this.historic = historic;
    }





    public String getCommentText() {
        return commentText;
    }

    public String setCommentText(String commentText) {
        this.commentText = commentText;
        return commentText;
    }
}
