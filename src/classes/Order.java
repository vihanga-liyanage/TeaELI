/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javax.swing.table.DefaultTableModel;
import java.util.*;

public class Order {

    //attributes
    private int placedBy, orderStatus;
    private String username, blendName, date, orderID, blendID;
    private ArrayList<Ingredient> ingredientList = new ArrayList();
    private ArrayList<Blend> blendList = new ArrayList();

    DBConnection dbConn = new DBConnection();

    //constructor
    public Order() {
        this.orderID = "";
        this.placedBy = 0;
        this.orderStatus = 0;
        this.blendID = "";
        this.username = null;
        this.blendName = null;
        this.date = null;
    }

    //getters and setters
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getPlacedBy() {
        return placedBy;
    }

    public void setPlacedBy(int placedBy) {
        this.placedBy = placedBy;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getblendID() {
        return blendID;
    }

    public void setblendID(String blendID) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    //Populate orderListTable in the order handling tab (NOT FINISHED........!!!!!!!!!)
    public void populateOrderListTable(DefaultTableModel tModel) {
        ResultArray resultSet;

        String query = "SELECT o.orderID, o.orderStatus, o.date, u.username FROM user u JOIN `order` o ON o.placedBy = u.userID ORDER BY o.orderStatus, o.orderID;";

        resultSet = dbConn.getResultArray(query);
        tModel.setRowCount(0);

        String status = null;
        while (resultSet.next()) {
            switch (resultSet.getString(1)) {
                case "0":
                    status = "Pending";
                    break;
                case "1":
                    status = "Received";
                    break;
                case "2":
                    status = "Completed";
                    break;
            }
            String date = resultSet.getString(2);
            date = date.substring(0, date.indexOf('.'));
            
            tModel.addRow(new Object[]{resultSet.getString(0), status, date, resultSet.getString(3)});
        }
    }

    //Getting last order ID
    public String getLastOrderID() {
        String query = "SELECT `orderID` FROM `order` ORDER BY `orderID` DESC LIMIT 0 , 1";
        ResultArray res = dbConn.getResultArray(query);
        String orderID = "";
        while (res.next()) {
            orderID = res.getString(0);
        }
        return orderID;
    }
    
    //Loading the orderComboBox in the order handling tab
    public ResultArray loadOrderComboBox(){
        ResultArray resultSet;
        String query = "SELECT orderID, orderStatus FROM `order` ORDER BY orderStatus,date;";
        resultSet = dbConn.getResultArray(query);
        
        return resultSet;
    }
    
    //view the selected order
    public Order viewOrder(DefaultTableModel tModelBlend, DefaultTableModel tModelIng, String orderID){
        Order temp = new Order();
        ResultArray resultSet1, resultSet2;
        tModelBlend.setRowCount(0);
        tModelIng.setRowCount(0);
        
        String query1 = "select o.orderID, o.date, ob.blendID, ob.requiredQty, ob.visibleStock, ob.invisibleStock, ob.balance, ob.excessQty, b.blendName from `order` o inner join orderblend ob on o.orderID = ob.orderID inner join blend b on ob.blendID = b.blendID where o.orderID = '"+orderID+"';";
        resultSet1 = dbConn.getResultArray(query1);
        while (resultSet1.next()){
            String req = formatNum(resultSet1.getString(3));
            String visible = formatNum(resultSet1.getString(4));
            String invisible = formatNum(resultSet1.getString(5));
            String balance = formatNum(resultSet1.getString(6));
            String exes = formatNum(resultSet1.getString(7));
            tModelBlend.addRow(new Object[]{resultSet1.getString(2), resultSet1.getString(8), req, visible, invisible, balance, exes});
            String date = resultSet1.getString(1);
            date = date.substring(0, date.indexOf('.'));
            temp.setDate(date);
        }
       
        String query2 = "select i.ingName, s.supName, oi.requiredQty, oi.visibleStock, oi.invisibleStock, oi.balance, oi.excessQty, oi.remarks, c.categoryName from orderingredient oi inner join ingredient i on oi.ingID = i.ingID inner join supplier s on i.supID = s.supID inner join ingredientcategory c on i.ingCategoryID = c.ingCategoryID where oi.orderID = '"+orderID+"';";
        resultSet2 = dbConn.getResultArray(query2);
        while (resultSet2.next()){
            String req = formatNum(resultSet2.getString(2));
            String visible = formatNum(resultSet2.getString(3));
            String invisible = formatNum(resultSet2.getString(4));
            String balance = resultSet2.getString(5);
            String exes = resultSet2.getString(6);
            String balanceF = formatNum(balance);
            String exesF = formatNum(exes);
            String finl = formatNum(Double.parseDouble(balance) + Double.parseDouble(exes) + "");
            tModelIng.addRow(new Object[]{resultSet2.getString(0), req, visible, invisible, balanceF, exesF, finl, resultSet2.getString(1), resultSet2.getString(8), 0, resultSet2.getString(7)});
        }
        
        temp.setOrderID(orderID);
        
        return temp;
    }
    
    //Placing an order
    public boolean placeOrder(String orderID){
        User user = new User();
        user.getIDByUsername();
        String query = "INSERT INTO `order` (`orderID` ,`placedBy` ,`orderStatus`) " +
                "VALUES ('" + orderID + "' , '" + user.getUserID() + "', '0')";
        return (dbConn.updateResult(query) == 1);
    }
    //placing orderBlends
    public boolean placeOrderBlends(String[] data) {
        String query = "INSERT INTO orderblend VALUES('" + data[0] + "', '" + data[1] + "', '" + data[2] + "', '" + data[3] + "', '" + data[4] + "', '" + data[5] + "', '" + data[6] + "')";
        return (dbConn.updateResult(query) == 1);
    }
    //placing orderIngredients
    public boolean placeOrderIngredients(String[] data) {
        String query = "INSERT INTO orderingredient VALUES('" + data[0] + "', '" + data[1] + "', '" + data[2] + "', '" + data[3] + "', '" + data[4] + "', '" + data[5] + "', '" + data[6] + "', '')";
        return (dbConn.updateResult(query) == 1);
    }
        
    public int updateOrderStatus(int status, String id){
        if(status == 1){
            String query = "UPDATE `order` SET orderStatus = 1 WHERE orderID = '"+id+"'";          
            int result = dbConn.updateResult(query);
            return result;
        }else if(status == 2){
            String query = "UPDATE `order` SET orderStatus = 2 WHERE orderID = '"+id+"'";          
            int result = dbConn.updateResult(query);
            return result;
        }
        return -1;
    }
    
    public int updateOrderRowWise(String oID, double additional, String remark, String ing){
        ResultArray resultSet0, resultSet1;
        
        String query0 = "SELECT ingID FROM ingredient WHERE ingName = '"+ing+"'";
        resultSet0 = dbConn.getResultArray(query0);
        int ingID = 0;
        while(resultSet0.next()){
           ingID = Integer.parseInt(resultSet0.getString(0));
           break;
        }

        String query1 = "SELECT excessQty FROM orderingredient WHERE orderID = '"+oID+"' AND ingID = '"+ingID+"'";
        resultSet1 = dbConn.getResultArray(query1);
        double exess = 0;
        while(resultSet1.next()){
            exess = Double.parseDouble(resultSet1.getString(0));
            break;
        }
        
        double newExess = exess + additional;
        
        String query2 = "UPDATE orderingredient SET excessQty = '"+newExess+"', remarks = '"+remark+"' WHERE orderID = '"+oID+"' AND ingID = '"+ingID+"'";          
        int result = dbConn.updateResult(query2);
        return result;
    }
    
    //formatting numbers to add commas
    private String formatNum(String num){
        String decimal=num, point = null;
        if(num.contains(".")){
            String[] temp = num.split("\\.");
            decimal = temp[0];
            point = temp[1];
        }
        int i = decimal.length();
        while (i > 3) {
            String part1 = decimal.substring(0, i-3);
            String part2 = decimal.substring(i-3);
            decimal = part1 + "," + part2;
            i-=3;
        }
        if (point != null){
            decimal += "." + point;
        }
        return decimal;
    }
    private String formatNum(int num){
        return formatNum(String.valueOf(num));
    }
    private String formatNum(float num){
        return formatNum(Float.toString(num));
    }
    
    //overiding Integer.parseInt() to accept nums with commas
    private int parseInt(String num){
        try{
            return Integer.parseInt(num);
        } catch (NumberFormatException e){
            if (num.matches("[[0-9]{1,2}+,]*")) {
                num = num.replace(",", "");
                return Integer.parseInt(num);
            }
        }
        return 0;
    }
    
    //overiding Float.parseFloat() to accept nums with commas
    private float parseFloat(String num){
        try{
            return Float.parseFloat(num);
        } catch (NumberFormatException e){
            if (num.matches("[[0-9]{1,2}+,]*.[0-9]*")) {
                num = num.replace(",", "");
                return Float.parseFloat(num);
            }
        }
        return 0;
    }

}
