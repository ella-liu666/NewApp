package com.haixiajiemei.app.Module.Setting.Model;

import java.io.Serializable;

public class Message implements Serializable {

    private int icon;

    private int content_icon;

//    private URL icon;
//
//    private URL content_icon;

    private String title;

    private String day_tiem;

    private String content;



    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getContent_icon() {
        return content_icon;
    }

    public void setContent_icon(int content_icon) {
        this.content_icon = content_icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDay_tiem() {
        return day_tiem;
    }

    public void setDay_tiem(String day_tiem) {
        this.day_tiem = day_tiem;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
