package com.example.shoppinglistapp;

import java.io.Serializable;

public class ItemListModel implements Serializable {
    private int itemID;
    private String itemName;
    private float itemCost;
    private String itemCategory;

    public ItemListModel() {
    }

    public ItemListModel(int itemID, String itemName, float itemCost, String itemCategory) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.itemCategory = itemCategory;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getItemCost() {
        return itemCost;
    }

    public void setItemCost(float itemCost) {
        this.itemCost = itemCost;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    @Override
    public String toString() {
        return  "Item ID = " + itemID + "\n"+
                "Item Name = '" + itemName + '\'' + "\n"+
                "Item Cost = " + itemCost + "\n"+
                "Item Category = '" + itemCategory + '\'';
    }
}
