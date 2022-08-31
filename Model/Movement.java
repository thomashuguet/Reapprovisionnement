package Model;

import java.math.BigDecimal;

public class Movement {
    
    private String id; 
    private BigDecimal qty; 
    //private String unity; 
    private int day; 
    //private Product product;

    public Movement(String id, BigDecimal qty, int day){
        this.day = day; 
        this.qty = qty; 
        this.id = id; 
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
   /* public String getUnity() {
        return unity;
    }
    public void setUnity(String unity) {
        this.unity = unity;
    }*/
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    /*public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    } */

}
