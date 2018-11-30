package com.lee.halu.du_an_1_mob.Model;

import java.io.Serializable;

public class Model implements Serializable {
    String idzone,zonename,name2;
    int price;

    public Model(String idzone, String zonename, String name2, int price) {
        this.idzone = idzone;
        this.zonename = zonename;
        this.name2 = name2;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Model(){

    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public Model(String idzone, String zonename, String name2) {
        this.idzone = idzone;
        this.zonename = zonename;
        this.name2 = name2;
    }

    public Model(String idzone, String zonename) {
        this.idzone = idzone;
        this.zonename = zonename;
    }

    public String getIdzone() {
        return idzone;
    }

    public void setIdzone(String idzone) {
        this.idzone = idzone;
    }

    public String getZonename() {
        return zonename;
    }

    public void setZonename(String zonename) {
        this.zonename = zonename;
    }
}
