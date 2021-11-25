/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservice.androidwebservice;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vorno
 */
@XmlRootElement
public class Item {
    private int itemID;
    private String itemName;
    private float itemCost;
    private String itemCategory;

    public Item() {
    }

    public Item(int itemID, String itemName, float itemCost, String itemCategory) {
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
}
