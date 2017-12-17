/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.Classes;

import java.util.Date;

/**
 *
 * @author ZIYAD
 */
public class Suppliers {
    
    
        public String invoice;
	public Date date;
	public String modeofpay;
        public String name;
        public float total;
        public float balance;
        public float given; 
        public String address;
        public String dln1;
        public String dln2;
        public String gst;

    public String getAddress() {
        return address;
    }

    public Suppliers(String name, String address, String dln1, String dln2, String gst) {
        this.name = name;
        this.address = address;
        this.dln1 = dln1;
        this.dln2 = dln2;
        this.gst = gst;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDln1() {
        return dln1;
    }

    public void setDln1(String dln1) {
        this.dln1 = dln1;
    }

    public String getDln2() {
        return dln2;
    }

    public void setDln2(String dln2) {
        this.dln2 = dln2;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

  
        
        public Suppliers(){
        this.invoice="";
	this.date=date;
	this.modeofpay="";
        this.name="";
        this.total=0;
        this.balance=0;
        this.given=0; 
        
    }

       
	
	public Suppliers( String invoice,
	 Date date,
	 String modeofpay,
         String name,
         float total,
         float balance,
         float given )
                
	{
		super();
                this.invoice=invoice;                
                this.date =date;
                this.modeofpay=modeofpay;
                this.name =name;
                this.total=total;
                this.balance = balance;
                this.given = given;
                
	}

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getModeofpay() {
        return modeofpay;
    }

    public void setModeofpay(String modeofpay) {
        this.modeofpay = modeofpay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getGiven() {
        return given;
    }

    public void setGiven(float given) {
        this.given = given;
    }
        
        
    
}
