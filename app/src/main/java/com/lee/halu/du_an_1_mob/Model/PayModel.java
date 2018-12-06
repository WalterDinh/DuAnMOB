package com.lee.halu.du_an_1_mob.Model;

import java.io.Serializable;

public class PayModel implements Serializable{
    String names;
    int price;
    int amount;
int pay;
    public PayModel() {
    }

    public PayModel(String name, int price, int amount, int pay) {
        this.names = name;
        this.price = price;
        this.amount = amount;
        this.pay=pay;
    }

    public String getName() {
        return names;
    }

    public void setName(String name) {
        this.names = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
