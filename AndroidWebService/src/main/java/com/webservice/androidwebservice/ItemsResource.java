/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservice.androidwebservice;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Vorno
 */
@Stateless
public class ItemsResource {
    private List<Item> list = new ArrayList<>();
    
    public ItemsResource() {
        initialiseItems();
    }
    
    public final List<Item> initialiseItems() {
        Item crackers = new Item(1, "Crackers", 4.50f, "Food");
        Item monitor = new Item(2, "Monitor", 350f, "Electronics");
        Item speakers = new Item(255, "Speakers", 75f, "Electronics");
        Item keyboard = new Item(6, "Keyboard", 120f, "Electronics");
        Item gum = new Item(4, "Gum", 1.50f, "Food");
        Item faceCream = new Item(5, "Face Cream", 8f, "Beauty");
        
        list.add(crackers);
        list.add(monitor);
        list.add(speakers);
        list.add(keyboard);
        list.add(gum);
        list.add(faceCream);
        
        return list;
    }

    public List<Item> getAllItems() {
        return list;
    }

    public Item getItem(String sentItem) {
        int itemID;
        
        try {
            itemID = Integer.parseInt(sentItem);
        }
        catch(NumberFormatException e) {
            System.err.println("Error parsing itemID: "+e);
            return null;
        }
        
        for(Item currItem : list) {
            if(currItem.getItemID() == itemID)
                return currItem;
        }
        
        System.err.println("ItemID not found in Item List");
        return null;
    }

    public String editItem(String editItem) {
        Gson gson = new Gson();
        
        editItem = jsonFormatter(editItem);
        
        JsonToItem jsonToItem = gson.fromJson(editItem, JsonToItem.class);
        
        boolean isValid = false;
        int currItemIDInt;
        int newItemIDInt;
        float itemCostFloat;
        
        try {
            currItemIDInt = Integer.parseInt(jsonToItem.getCurrItemID());
            newItemIDInt = Integer.parseInt(jsonToItem.getNewItemID());
            itemCostFloat = Float.parseFloat(jsonToItem.getItemCost());
        }
        catch (NumberFormatException e) {
            System.err.println("Error parsing currItemID, newItemID or costID while EDITING");
            return "UNSUCCESSFUL";
        }
        for(Item currItem : list) {
            if(currItem.getItemID() == newItemIDInt) {
                return "UNSUCCESSFUL: ITEM_ID ALREADY EXISTS";
            }
        }
        
        for(Item currItem : list) {
            if(currItem.getItemID() == currItemIDInt) {
                isValid = true;
                currItem.setItemID(newItemIDInt);
                currItem.setItemName(jsonToItem.getItemName());
                currItem.setItemCost(itemCostFloat);
                currItem.setItemCategory(jsonToItem.getItemCategory());
            }
        }
        if(isValid)
            return "SUCCESSFUL";
        else
            return "UNSUCCESSFUL";
    }

    public String createItem(String createItem) {
        Gson gson = new Gson();
        
        createItem = jsonFormatter(createItem);
        
        JsonToItem jsonToItem = gson.fromJson(createItem, JsonToItem.class);
        
        int itemIDInt;
        float itemCostFloat;
        try {
            itemIDInt = Integer.parseInt(jsonToItem.getNewItemID());
            itemCostFloat = Float.parseFloat(jsonToItem.getItemCost());
        }
        catch (NumberFormatException e) {
            return "UNSUCCESSFUL";
        }
        try {
            for(Item currItem : list) {
                if(currItem.getItemID() == itemIDInt) {
                    return "UNSUCCESSFUL: ITEM_ID ALREADY EXISTS";
                }
            }
            Item newItem = new Item(itemIDInt, jsonToItem.getItemName(), itemCostFloat, jsonToItem.getItemCategory());
            list.add(newItem);
        }
        catch(Exception e) {
            return "UNSUCCESSFUL";
        }
        return "SUCCESSFUL";
    }
    
    public String jsonFormatter(String formatThis) {
        formatThis = formatThis.replaceAll("%22&%22", "\",\"");
        formatThis = formatThis.replaceAll("%22", "\"");
        formatThis = formatThis.replaceAll("=", ":");
        formatThis = formatThis.replaceAll("&", "");
        formatThis = formatThis.replaceAll("\\+", "");
        formatThis = "{"+formatThis;
        formatThis += "}";
        
        return formatThis;
    }
    
    class JsonToItem {
        private String currItemID;
        private String newItemID;
        private String itemName;
        private String itemCost;
        private String itemCategory;

        public JsonToItem() {
        }
        
        public String getCurrItemID() {
            return currItemID;
        }

        public void setCurrItemID(String currItemID) {
            this.currItemID = currItemID;
        }

        public String getNewItemID() {
            return newItemID;
        }

        public void setNewItemID(String newItemID) {
            this.newItemID = newItemID;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getItemCost() {
            return itemCost;
        }

        public void setItemCost(String itemCost) {
            this.itemCost = itemCost;
        }

        public String getItemCategory() {
            return itemCategory;
        }

        public void setItemCategory(String itemCategory) {
            this.itemCategory = itemCategory;
        }
        
        
    }
}
