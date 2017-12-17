/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.salesperson;

/**
 *
 * @author ZIYAD
 */
public class Sperson
{       public int id;
        public int q_id;
	public String name;
        public String address;
	 public int mobile;
	public int points;
        
        
        public Sperson(){
        this.id=0;
        this.q_id=0;
        this.name = "";
        this.address = "";
        this.points = 0;
        this.mobile = 0;
    }

       
	
	public Sperson(int id, String name,int q_id ,  int points ,int mobile,String address)
	{
		super();
                this.id= id;
                this.q_id= q_id;
		this.name = name;
		this.points=points;
		this.mobile = mobile;
                this.address=address;
                
	}

	public int getId() {
		return id;
	}
        public int getqId() {
		return q_id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}
        
        public int getPoint() {
		return points;
	}
        
        public int getMobile() {
		return mobile;
	}
        
        
	/////////////
        
	public void setName(String name) {
		this.name=name;
	}
        
        public void setAddress(String address) {
		this.address=address;
	}
	public void setId(int id) {
		this.id=id;
	}
        
        public void setqId(int q_id) {
		this.q_id=q_id;
	}

	public void setMobile(int mobile ) {
		this.mobile=mobile;
	}
	
        
        public void setPoint(int points) {
		this.points=points;
	}
	
        

}
