package com.lee.halu.du_an_1_mob.Model;

import java.io.Serializable;

public class FoodModel implements Serializable {
    String idfood,foodname,typefood;
    int price;

    public FoodModel() {

    }

    public String getIdfood() {
        return idfood;
    }

    public void setIdfood(String idfood) {
        this.idfood = idfood;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getTypefood() {
        return typefood;
    }

    public void setTypefood(String typefood) {
        this.typefood = typefood;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
