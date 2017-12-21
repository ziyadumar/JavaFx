/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnection.JavaClass;

/**
 *
 * @author HP
 */
public class SampleTable {
    
    public int sino;
    public String name;

    public SampleTable(int sino, String name) {
        this.sino = sino;
        this.name = name;
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
    
    
    
}
