package Model;

public class Product {
    private String id; 
    private String reference;

    public Product(String id, String reference) {
        this.id        = id;
        this.reference = reference;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    } 
}
