/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.ResultSet;
<<<<<<< HEAD
import java.sql.SQLException;
import java.util.*;
import javax.swing.table.DefaultTableModel;
=======
import java.util.*;
import javax.swing.JComboBox;
>>>>>>> b1d332c849304843173e60d93273942440109da4

public class Order {
    //attributes
    private int orderID,placedBy,orderStatus,blendID;
    private String username,blendName;
    private ArrayList<Ingredient> ingredientList = new ArrayList();
    private ArrayList<Blend> blendList = new ArrayList();
    private Date date;
    
    DBConnection dbConn = new DBConnection();
    
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
    
<<<<<<< HEAD
    //Populate orderListTable in the order handling tab (NOT FINISHED........!!!!!!!!!)
    public void populateorderListTable(DefaultTableModel tModel) {
        Connection connection = null;
        ResultSet resultSet;
        try {
            connection = dbConn.setConnection();
        } catch (SQLException e) {

        }

        String query = "SELECT o.orderID, o.orderStatus, o.date, u.username FROM user u JOIN `order` o ON o.placedBy = u.userID ORDER BY o.orderStatus;";

        resultSet = dbConn.getResult(query, connection);
        try {
            tModel.setRowCount(0);
            
            String status = null;
            while (resultSet.next()) {
                if(resultSet.getInt(2) == 0){
                    status = "Pending";
                }else if(resultSet.getInt(2) == 1){
                    status = "Received";
                }
                tModel.addRow(new Object[]{resultSet.getString(1), status, resultSet.getString(3), resultSet.getString(4)});
            }
        } catch (SQLException ex) {
            System.out.println(ex);
=======
    //Getting last order ID
    public String getLastOrderID(){
        Connection conn = null;
        ResultSet resultSet = null;
        
        try{
            String query = "SELECT `orderID` FROM `order` ORDER BY `orderID` DESC LIMIT 0 , 1";
            
            conn = dbConn.setConnection();
            resultSet = dbConn.getResult(query, conn);
            String orderID = "";
            while(resultSet.next()){
                orderID = resultSet.getString(1);
            }
            return orderID;
            
        }catch(Exception e){
            System.err.println("err : " + e);
>>>>>>> b1d332c849304843173e60d93273942440109da4
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
<<<<<<< HEAD
                } catch (SQLException e) {
                    System.err.println("Resultset close error : " + e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
=======
                } catch (Exception e) {
                    System.err.println("Resultset close error : " + e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
>>>>>>> b1d332c849304843173e60d93273942440109da4
                    System.err.println("Connection close error : " + e);
                }
            }
        }
<<<<<<< HEAD
=======
        return null;
>>>>>>> b1d332c849304843173e60d93273942440109da4
    }
}

