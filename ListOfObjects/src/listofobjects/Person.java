/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listofobjects;

/**
 *
 * @author ZIYAD
 */
public class Person {
    
    public String name;
    
    public String contact;
    
    public String address;

    public Person(String name, String contact, String address) {
        this.name = name ;
        this.contact = contact ;
        this.address = address ;
    }

    // A default constructor, [like "Person person2 = new Person();" ] when no arguments are passed ,saves the following values to each variables.
    Person() {
        this.name = "Jasper" ;
        this.contact = "412 Grape Street" ;
        this.address = "Woodsville" ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
}
