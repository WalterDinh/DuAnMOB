package com.lee.halu.du_an_1_mob.Model;

import java.io.Serializable;

public class StatisticalModel implements Serializable {
    String foodname;
    int pay,count;

    public StatisticalModel() {
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
