package com.lee.halu.du_an_1_mob.Model;

import java.io.Serializable;

public class Model implements Serializable {
    String idzone,zonename;
    public Model(){

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
