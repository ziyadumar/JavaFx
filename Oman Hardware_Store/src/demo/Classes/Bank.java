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
public class Bank {
    
    public int sino;
    public String name;
    public String accountno;
    public float balance;

    public Bank(int sino, String name, String accountno, float balance) {
        this.sino = sino;
        this.name = name;
        this.accountno = accountno;
        this.balance = balance;
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

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
    
    
    
    
            
    
}
