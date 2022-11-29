package products;

public class Product {
    private String id;
    private String name;
    private String localBrand;
    private double price;
    private int amount;


    public Product() {
    }

    public Product(String id, String name, String localBrand, double price, int amount) {
        this.id = id;
        this.name = name;
        this.localBrand = localBrand;
        this.price = price;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalBrand() {
        return localBrand;
    }

    public void setLocalBrand(String localBrand) {
        this.localBrand = localBrand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }



    @Override
    public String toString() {
        return id + "," +  name + "," +  localBrand + "," + price +"," + amount;
    }
}
