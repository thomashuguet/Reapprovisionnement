package Model;

import java.math.BigDecimal;
import java.util.List;

public class Stock {

    private String id;
    private BigDecimal stkInit; 
    private int couvMin; 
    private int couvCible; 
    private Product product; 
    private List<Movement> movList;

    public Stock(String id, Product product, BigDecimal stkInit, int couvMin, int couvCible, List<Movement> movList) {
        this.id         = id;
        this.product    = product; 
        this.stkInit    = stkInit;
        this.couvMin    = couvMin;
        this.couvCible  = couvCible;
        this.movList    = movList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Stock() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getStkInit() {
        return stkInit;
    }

    public void setStkInit(BigDecimal stkInit) {
        this.stkInit = stkInit;
    }

    public int getCouvMin() {
        return couvMin;
    }

    public void setCouvMin(int couvMin) {
        this.couvMin = couvMin;
    }

    public int getCouvCible() {
        return couvCible;
    }

    public void setCouvCible(int couvCible) {
        this.couvCible = couvCible;
    }

    public List<Movement> getMovList() {
        return movList;
    }

    public void setMovList(List<Movement> movList) {
        this.movList = movList;
    } 

    
}