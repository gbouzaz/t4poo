package Classes;


public class Sale {
    public String userID;
    public String productName;
    public String date;
    public String qtd;
    public String totalCost;
    
    public Sale(){
        
    }
    
    public Sale(String id, String name, String date, String qtd, String totalCost){
        this.userID = id;
        this.productName = name;
        this.date = date;
        this.qtd = qtd;
        this.totalCost = totalCost;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the qtd
     */
    public String getQtd() {
        return qtd;
    }

    /**
     * @param qtd the qtd to set
     */
    public void setQtd(String qtd) {
        this.qtd = qtd;
    }

    /**
     * @return the totalCost
     */
    public String getTotalCost() {
        return totalCost;
    }

    /**
     * @param totalCost the totalCost to set
     */
    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }
}
