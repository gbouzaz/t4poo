/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;


public class Product {
    public String name;
    public String price;
    public String validate;
    public String provider;
    public String qtd;
    
    public Product(){
        
    }
    
    public Product(String name, String price, String validate, String provider){
        this.name = name;
        this.price = price;
        this.validate = validate;
        this.provider = provider;
        this.qtd = "0";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return the validate
     */
    public String getValidate() {
        return validate;
    }

    /**
     * @param validate the validate to set
     */
    public void setValidate(String validate) {
        this.validate = validate;
    }

    /**
     * @return the provider
     */
    public String getProvider() {
        return provider;
    }

    /**
     * @param provider the provider to set
     */
    public void setProvider(String provider) {
        this.provider = provider;
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
}
