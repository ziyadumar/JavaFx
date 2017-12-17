


package demo;
/**
 *
 * @author ZIYAD
 */
public class Items
{       public int id_ITEMS;
	public String name;
	 public int price;
	public int quantity;
        
        public Items(){
        this.id_ITEMS=0;
        this.name = "";
        this.price = 0;
        this.quantity = 0;
    }

       
	
	public Items(int id_ITEMS, String name, int quantity, int price)
	{
		super();
                this.id_ITEMS= id_ITEMS;
		this.name = name;
		this.price=price;
		this.quantity = quantity;
                
	}

	public int getId() {
		return id_ITEMS;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}
        
        public int getPrice() {
		return price;
	}
        
	
	public void setName(String name) {
		this.name=name;
	}

	public void setId(int id_ITEMS) {
		this.id_ITEMS=id_ITEMS;
	}

	public void setQuantity(int quantity ) {
		this.quantity=quantity;
	}
	
        
        public void setPrice(int price) {
		this.price=price;
	}
	

}

