package com.lee.halu.du_an_1_mob.Model;

import java.io.Serializable;

public class DiagramModel implements Serializable {
    public DiagramModel() {
    }
    String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public DiagramModel(String tableName) {

        this.tableName = tableName;
    }
}
