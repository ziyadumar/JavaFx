/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.Classes;

/**
 *
 * @author ZIYAD
 */
public class Asset {
    
    public int sino;
    public String name;
    public String description;
    public Float amount;

    public Asset(int sino, String name, String description, Float amount) {
        this.sino = sino;
        this.name = name;
        this.description = description;
        this.amount = amount;
    }
    
    

    public int getSino() {
        return sino;
    }

    public void setSino(int sino) {
        this.sino = sino;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
    
    
    
    
}
