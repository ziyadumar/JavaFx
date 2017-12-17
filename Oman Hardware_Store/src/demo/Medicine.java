/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.util.Date;

/**
 *
 * @author ZIYAD
 */
public class Medicine {
        public int id;
	public String name;
	public float price;
	public String batch;
        public String exp;
        public int quantity;
        public String contentname;
        public float gst;
        public float rate;
        public String mfr;
        public float total; 
        public int sino;
        public int free;
        public float margin;
        public  String rdate;
        public int pack;
        public String categ;

    public int getPack() {
        return pack;
    }

    public void setPack(int pack) {
        this.pack = pack;
    }

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }
        

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public float getMargin() {
        return margin;
    }

    public void setMargin(float margin) {
        this.margin = margin;
    }

  
        
        public Medicine(){
        this.id=0;
        this.name = "";
        this.price = 0;
        this.batch="";
        this.exp="";
        this.quantity = 0;
        this.contentname="";
        this.gst=0;
        this.rate=0;
        this.mfr="";
        
    }

       
	
	public Medicine(int id, String name, int quantity, float price,String batch,String exp)
	{
		super();
                this.id=id;
                this.name =name;
                this.price =price;
                this.batch=batch;
                this.exp=exp;
                this.quantity = quantity;
                
	}
        
        public Medicine(int id, String name, int quantity, float price,String batch,String exp,String mfr)
	{
		super();
                this.id=id;
                this.name =name;
                this.price =price;
                this.batch=batch;
                this.exp=exp;
                this.quantity = quantity;
                this.mfr=mfr;
                
	}
        
        public Medicine(int id, String name, int quantity, float price,String batch,String exp,String mfr,String contentname)
	{
		super();
                this.id=id;
                this.name =name;
                this.price =price;
                this.batch=batch;
                this.exp=exp;
                this.quantity = quantity;
                this.mfr=mfr;
                this.contentname=contentname;
                
	}
         public Medicine(int id, String name, int quantity, float price,String batch,String exp,String mfr,String contentname,int sino)
	{
		super();
                this.id=id;
                this.name =name;
                this.price =price;
                this.batch=batch;
                this.exp=exp;
                this.quantity = quantity;
                this.mfr=mfr;
                this.contentname=contentname;
                this.sino=sino;
                
	}
         
         public Medicine(int id, String name, int quantity, float price,String batch,String exp,String mfr,String contentname,int sino, float rate, float gst,float margin)
	{
		super();
                this.id=id;
                this.name =name;
                this.price =price;
                this.batch=batch;
                this.exp=exp;
                this.quantity = quantity;
                this.mfr=mfr;
                this.contentname=contentname;
                this.sino=sino;
                this.rate=rate;
                this.gst=gst;
                this.margin=margin;
                       
                
	}
         
         public Medicine(int id, String name, int quantity, float price,String batch,String mfr,String contentname,int sino, float rate, float gst,float margin)
	{
		super();
                this.id=id;
                this.name =name;
                this.price =price;
                this.batch=batch;
                this.quantity = quantity;
                this.mfr=mfr;
                this.contentname=contentname;
                this.sino=sino;
                this.rate=rate;
                this.gst=gst;
                this.margin=margin;
                       
                
	}
         
         
           public Medicine(int id, String name, int quantity, float price,String batch,String exp,String mfr,String contentname,int sino, float rate, float gst,float margin,String rdate)
	{
		super();
                this.id=id;
                this.name =name;
                this.price =price;
                this.batch=batch;
                this.exp=exp;
                this.quantity = quantity;
                this.mfr=mfr;
                this.contentname=contentname;
                this.sino=sino;
                this.rate=rate;
                this.gst=gst;
                this.margin=margin;
                this.rdate=rdate;
                       
                
	}
           
           
           public Medicine(int id, String name, int quantity, float price,String batch,String exp,String mfr,String contentname,int sino, float rate, float gst,String margin,String rdate)
	{
		super();
                this.id=id;
                this.name =name;
                this.price =price;
                this.batch=batch;
                this.exp=exp;
                this.quantity = quantity;
                this.mfr=mfr;
                this.contentname=contentname;
                this.sino=sino;
                this.rate=rate;
                this.gst=gst;
                this.margin=Float.valueOf(margin);
                this.rdate=rdate;
                       
                
	}
           
           
        public Medicine(int id, String name, int quantity, float price,String batch,String exp, String contentname, float gst, float rate, String mfr, float total)
	{
		super();
                this.id=id;
                this.name =name;
                this.price =price;
                this.batch=batch;
                this.exp=exp;
                this.quantity = quantity;
                this.contentname=contentname;
                this.gst=gst;
                this.rate=rate;
                this.mfr=mfr;
                this.total=total;
                
                
	}
        
         public Medicine(int id, String name, int quantity, float price,String batch,String exp, String contentname, float gst, float rate, String mfr, float total, int sino)
	{
		super();
                this.id=id;
                this.name =name;
                this.price =price;
                this.batch=batch;
                this.exp=exp;
                this.quantity = quantity;
                this.contentname=contentname;
                this.gst=gst;
                this.rate=rate;
                this.mfr=mfr;
                this.total=total;
                this.sino=sino;
                
                
	}
         
         public Medicine(int id, String name, int quantity, float price,String batch,String exp, String contentname, float gst, float rate, String mfr, float total, int sino, int free , int pack ,String categ)
	{
		super();
                this.id=id;
                this.name =name;
                this.price =price;
                this.batch=batch;
                this.exp=exp;
                this.quantity = quantity;
                this.contentname=contentname;
                this.gst=gst;
                this.rate=rate;
                this.mfr=mfr;
                this.total=total;
                this.sino=sino;
                this.free=free;
                this.pack=pack;
                this.categ=categ;
                
	}

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public int getSino() {
        return sino;
    }

    public void setSino(int sino) {
        this.sino = sino;
    }
        
          public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
        

    public String getContentname() {
        return contentname;
    }

    public void setContentname(String contentname) {
        this.contentname = contentname;
    }

    public float getGst() {
        return gst;
    }

    public void setGst(float gst) {
        this.gst = gst;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getMfr() {
        return mfr;
    }

    public void setMfr(String mfr) {
        this.mfr = mfr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
 
	

}

