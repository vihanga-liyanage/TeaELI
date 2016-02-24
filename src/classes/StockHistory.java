package classes;

import java.util.Date;

public class StockHistory {
    private String name, reason, updatedBy;
    private Date date;
    private int oldQty, updatedQty;

    public StockHistory() {
        this.name = "";
        this.reason = "";
        this.updatedBy = "";
        this.date = null;
        this.oldQty = 0;
        this.updatedQty = 0;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getOldQty() {
        return oldQty;
    }

    public void setOldQty(int oldQty) {
        this.oldQty = oldQty;
    }

    public int getUpdatedQty() {
        return updatedQty;
    }

    public void setUpdatedQty(int updatedQty) {
        this.updatedQty = updatedQty;
    }
}
