package com.lee.halu.du_an_1_mob.Model;

import java.io.Serializable;

public class TypeFoodModel implements Serializable {
    String idtypefood,typefoodname;

    public TypeFoodModel() {
    }

    public String getIdtypefood() {
        return idtypefood;
    }

    public void setIdtypefood(String idtypefood) {
        this.idtypefood = idtypefood;
    }

    public String getTypefoodname() {
        return typefoodname;
    }

    public void setTypefoodname(String typefoodname) {
        this.typefoodname = typefoodname;
    }
}
