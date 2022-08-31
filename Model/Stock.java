package Model;

import java.math.BigDecimal;
import java.util.List;

public class Stock {

    private String id;
    private BigDecimal qty; 
    
    private int couvMin; 
    private int couvCible; 

    private List<Movement> movList; 

    
}