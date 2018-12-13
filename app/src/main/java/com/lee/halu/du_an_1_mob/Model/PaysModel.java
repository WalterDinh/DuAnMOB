package com.lee.halu.du_an_1_mob.Model;

import java.io.Serializable;

public class PaysModel implements Serializable {
    String amoung,name,time;
int sl;
    public PaysModel() {
    }

    public PaysModel(String amoung, String time,int sl) {
        this.amoung = amoung;
        this.name = name;
        this.time = time;
        this.sl=sl;
    }

    public String getAmoung() {
        return amoung;
    }

    public void setAmoung(String amoung) {
        this.amoung = amoung;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }
}
