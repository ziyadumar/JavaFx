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
public class BankTransVar {
    
    
    private static String cheque;
    private static String invoice;
    private static Date date;

    public static String getCheque() {
        return cheque;
    }

    public static void setCheque(String cheque) {
        BankTransVar.cheque = cheque;
    }

    public static String getInvoice() {
        return invoice;
    }

    public static void setInvoice(String invoice) {
        BankTransVar.invoice = invoice;
    }

    public static Date getDate() {
        return date;
    }

    public static void setDate(Date date) {
        BankTransVar.date = date;
    }
    
    


    
}
