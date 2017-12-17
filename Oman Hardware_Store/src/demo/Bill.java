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
public class Bill {
    
        public int sino;
	public String name;
	public float price;
        public int quantity;
        public String expiry;
        public String batch;
        public String mfr;
        public float total;
        public int id;
        
        
        public Bill(){
        this.sino=0;    
        this.name = "";
        this.price = 0;
        this.quantity = 0;
        this.expiry="";
        this.batch="";
        this.mfr="";
        this.total=0;
        this.id=0;

    }

    public int getSino() {
        return sino;
    }

    public void setSino(int sino) {
        this.sino = sino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

       
	
	public Bill( String name, int quantity, float price,String expiry,String batch,String mfr,float total, int id ,int sino)
	{
		super();
                this.name =name;
                this.price =price;
                this.quantity = quantity;
                this.expiry = expiry;
                this.batch = batch;
                this.mfr = mfr;
                this.total=total;
                this.id=id;
                this.sino=sino;
	}
        
        
        
	public Bill( String name, int quantity, float price , String mfr,float total, int sino)
	{
		super();
                this.name =name;
                this.price =price;
                this.quantity = quantity;
                this.mfr = mfr;
                this.total=total;
                this.sino=sino;
	}

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getMfr() {
        return mfr;
    }

    public void setMfr(String mfr) {
        this.mfr = mfr;
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

    
    
    
}
