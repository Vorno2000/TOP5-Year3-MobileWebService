/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservice.androidwebservice;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Vorno
 */
@Named
@Path("/webservice")
public class ItemService {
    @EJB
    ItemsResource items;
    
    @Path("/android/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getAllItems() {
        return items.getAllItems();
    }

    @Path("/android/getitem/{itemID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Item getItem(@PathParam("itemID") String sentItem) {
        return items.getItem(sentItem);
    }

    @Path("/android/edititem")
    @PUT
    public String editItem(String editItem) {
        return items.editItem(editItem);
    }

    @Path("/android/createitem")
    @POST
    public String createItem(String createItem) {
        return items.createItem(createItem);
    }
    
    @Path("/webapp/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getAllItemsWeb() {
        return items.getAllItems();
    }

    @Path("/webapp/getitem/{itemID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Item getItemWeb(@PathParam("itemID") String sentItem) {
        return items.getItem(sentItem);
    }

    @Path("/webapp/edititem")
    @PUT
    public String editItemWeb(String editItem) {
        return items.editItem(editItem);
    }

    @Path("/webapp/createitem")
    @POST
    public void createItemWeb(String createItem) {
        items.createItem(createItem);
    }

    public ItemsResource getItems() {
        return items;
    }
    
    
}
