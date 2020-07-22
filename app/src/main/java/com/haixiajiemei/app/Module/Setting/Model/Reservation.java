package com.haixiajiemei.app.Module.Setting.Model;

import java.io.Serializable;

public class Reservation implements Serializable {

//    private URL icon;

    private int icon;

    private String title;

    private String day_tiem;

    private String distance;



    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
