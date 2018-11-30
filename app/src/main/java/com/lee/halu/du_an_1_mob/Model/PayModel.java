package com.lee.halu.du_an_1_mob.Model;

import java.io.Serializable;

public class PayModel implements Serializable{
    String name;
    double price,pay;
    int amount;

    public PayModel(String name, double price, double pay, int amount) {
        this.name = name;
        this.price = price;
        this.pay = pay;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
