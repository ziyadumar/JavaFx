/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.transactions;

/**
 *
 * @author ZIYAD
 */
public class TransactionHistory {
    
    public int billno;
    public String date;
    public String customer;
    public float amount;
    
    
    public TransactionHistory(int billno, String date, String customer,float amount){
    this.billno=billno;
    this.date=date;
    this.customer=customer;
    this.amount=amount;
    
    
    }

    public int getBillno() {
        return billno;
    }

    public void setBillno(int billno) {
        this.billno = billno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    
}
