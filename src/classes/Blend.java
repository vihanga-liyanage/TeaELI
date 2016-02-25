package classes;

import java.util.ArrayList;

public class Blend {
    
    //attributes
    private String blendID, blendName, baseName;
    private int baseID, visibleStock, orderedStock, invisibleStock;
    private int orderReqQty , orderExcessQty ;
    private ArrayList<Ingredient> ingredientArray = new ArrayList();
    private ArrayList<Ingredient> flavourArray = new ArrayList();

    //constructor
    public Blend() {
        this.blendID = "";
        this.blendName = "";
        this.baseName = "";
        this.baseID = 0;
        this.visibleStock = 0;
        this.orderedStock = 0;
        this.invisibleStock = 0;
    }
    
    /* Start of setters and getters */
    public String getBlendID() {
        return blendID;
    }

    public void setBlendID(String blendID) {
        this.blendID = blendID;
    }

    public String getBlendName() {
        return blendName;
    }

    public void setBlendName(String blendName) {
        this.blendName = blendName;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public int getBaseID() {
        return baseID;
    }

    public void setBaseID(int baseID) {
        this.baseID = baseID;
    }

    public int getVisibleStock() {
        return visibleStock;
    }

    public void setVisibleStock(int visibleStock) {
        this.visibleStock = visibleStock;
    }

    public int getOrderedStock() {
        return orderedStock;
    }

    public void setOrderedStock(int orderedStock) {
        this.orderedStock = orderedStock;
    }

    public int getInvisibleStock() {
        return invisibleStock;
    }

    public void setInvisibleStock(int invisibleStock) {
        this.invisibleStock = invisibleStock;
    }

    public int getOrderReqQty() {
        return orderReqQty;
    }

    public void setOrderReqQty(int orderReqQty) {
        this.orderReqQty = orderReqQty;
    }

    public int getOrderExcessQty() {
        return orderExcessQty;
    }

    public void setOrderExcessQty(int orderExcessQty) {
        this.orderExcessQty = orderExcessQty;
    }

    public ArrayList<Ingredient> getIngredientArray() {
        return ingredientArray;
    }

    public void setIngredientArray(ArrayList<Ingredient> ingredientArray) {
        this.ingredientArray = ingredientArray;
    }

    public ArrayList<Ingredient> getFlavourArray() {
        return flavourArray;
    }

    public void setFlavourArray(ArrayList<Ingredient> flavourArray) {
        this.flavourArray = flavourArray;
    }
    /* End of setters and getters */
}
