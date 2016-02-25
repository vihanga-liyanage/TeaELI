/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.*;

public class Order {
    //attributes
    private int orderID,placedBy,orderStatus,blendID;
    private String username,blendName;
    private ArrayList<Ingredient> ingredientList = new ArrayList();
    private ArrayList<Blend> blendList = new ArrayList();
    private Date date;
    
    //constructor
    public Order(){
        this.orderID = 0;
        this.placedBy =0;
        this.orderStatus =0;
        this.blendID = 0;
        this.username = null;
        this.blendName = null;
        this.date = null;
    }
    
    //getters and setters
    public int getOrderID(){
        return orderID;
    }
    
    public void setOrderID(int orderID){
        this.orderID = orderID;
    }
    
    public int getPlacedBy(){
        return placedBy;
    }
    
    public void setPlacedBy(int placedBy){
        this.placedBy = placedBy;
    }
    
    public int getOrderStatus(){
        return orderStatus;
    }
    
    public void setOrderStatus(int orderStatus){
        this.orderStatus = orderStatus;
    }
    
    public int getblendID(){
        return blendID;
    }
    
    public void setblendID(int blendID){
        this.blendID = blendID;
    }
    
    public String getBlendName() {
        return blendName;
    }

    public void setIngName(String blendName) {
        this.blendName = blendName;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public Date getDate(){
        return date;
    }
    
    public void setDate(Date date){
        this.date = date;
    }
    
    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(ArrayList<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }
    
    public ArrayList<Blend> getBlendList() {
        return blendList;
    }

    public void setBlendList(ArrayList<Blend> blendList) {
        this.blendList = blendList;
    }
}

