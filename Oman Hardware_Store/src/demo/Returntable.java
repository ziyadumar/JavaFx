/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

/**
 *
 * @author ZIYAD
 */
public class Returntable {
    
    public String name;
    public float price;
    public int quantity;
    public float total;
    
    public Returntable(){
        
        this.name="";
    this.price=0;
    this.quantity=0;
    this.total=0;
    
    
    
    }
    
    public Returntable(String name , float price , int quantity , float total){
        
        this.name=name;
    this.price=price;
    this.quantity=quantity;
    this.total=total;
    
    
    
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
