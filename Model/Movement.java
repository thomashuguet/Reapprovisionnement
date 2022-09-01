package Model;

import java.math.BigDecimal;

public class Movement {
    
    private String      id; 
    private BigDecimal  qty; 
    private int         day; 
    private Product     product;
    //private String      unity; 

    public Movement(String id, Product product, BigDecimal qty, int day){
        this.day     = day; 
        this.product = product; 
        this.qty     = qty; 
        this.id      = id; 
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public BigDecimal getQty() {
        return qty;
    }
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }
   
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
}
