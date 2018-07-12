package com.example.eltobgy.teleprompterapp.models;

/**
 * Created by Eltobgy on 12-Jul-18.
 */

public class Teleprompter {


    private int scrollSpeed;
    private int fontSize;
    private String title;
    private String content;
    private int backgroundColor;
    private int fontColor;

    public Teleprompter() {
    }


    public int getScrollSpeed() {
        return scrollSpeed;
    }

    public void setScrollSpeed(int scrollSpeed) {
        this.scrollSpeed = scrollSpeed;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}