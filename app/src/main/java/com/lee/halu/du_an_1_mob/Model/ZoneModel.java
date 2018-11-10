package com.lee.halu.du_an_1_mob.Model;

import java.io.Serializable;

public class ZoneModel implements Serializable {
    String idzone,zonename;
    public  ZoneModel(){

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
