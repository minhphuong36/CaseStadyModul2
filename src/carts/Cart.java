package carts;

import account.Account;
import products.Product;

import java.util.List;

public class Cart {
    private   String user;
    private String nameProduct;
    private double priceProduct;
    private int amountProduct;
    private String localBrand;

    public Cart(){

    }

    public Cart(String user, String nameProduct, double priceProduct, int amountProduct, String localBrand) {
        this.user = user;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.amountProduct = amountProduct;
        this.localBrand = localBrand;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public int getAmountProduct() {
        return amountProduct;
    }

    public void setAmountProduct(int amountProduct) {
        this.amountProduct = amountProduct;
    }

    public String getLocalBrand() {
        return localBrand;
    }

    public void setLocalBrand(String localBrand) {
        this.localBrand = localBrand;
    }

    @Override
    public String toString() {
        return user + "," + nameProduct + "," + priceProduct +","+ amountProduct + ","+ localBrand ;
    }
}
