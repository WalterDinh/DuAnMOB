package com.lee.halu.du_an_1_mob.Model;

import java.io.Serializable;

public class OrderModel implements Serializable{
    String tablename,timeuse,pay1;

    public String getTablename() {
        return tablename;
    }

    public OrderModel(String tablename, String timeuse, String pay1) {
        this.tablename = tablename;
        this.timeuse = timeuse;
        this.pay1 = pay1;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getTimeuse() {
        return timeuse;
    }

    public void setTimeuse(String timeuse) {
        this.timeuse = timeuse;
    }

    public String getPay1() {
        return pay1;
    }

    public void setPay1(String pay1) {
        this.pay1 = pay1;
    }

    public OrderModel() {
    }

}
