package com.lee.halu.du_an_1_mob.Model;

import java.io.Serializable;

public class BillModel implements Serializable {
    String idbill,tablename;
    String timefinish;
    int pay;

    public BillModel() {
    }

    public String getIdbill() {
        return idbill;
    }

    public void setIdbill(String idbill) {
        this.idbill = idbill;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getTimefinish() {
        return timefinish;
    }

    public void setTimefinish(String timefinish) {
        this.timefinish = timefinish;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }
}
