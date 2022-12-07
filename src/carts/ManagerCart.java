package carts;

import products.Product;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ManagerCart {
    public static List<Cart> carts = new ArrayList<>();



    public static void writeToFileCart(Cart o) {
        try {
            carts.add(o);
            FileWriter fw = new FileWriter("cart.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(o.toString());
            bw.newLine();
            bw.close();
            fw.close();

        } catch (Exception e) {

        }
    }

    public static List<Cart> readFromFileCart() {
        try {
            FileReader fr = new FileReader("cart.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(",");
                String user = txt[0];
                String nameProduct = txt[1];
                double priceProduct = Double.parseDouble(txt[2]);
                int amountProduct = Integer.parseInt(txt[3]);
                String localBrand = txt[4];

                carts.add(new Cart(user,nameProduct, priceProduct,amountProduct,localBrand));


            }
        } catch (Exception e) {
        }
        return carts;
    }


    public static void showCartUser(List<Cart> carts) {
        System.out.printf("%-25s%-25s%-25s%-25s%-25s\n", " ", "Name Product","localBrand", "Price Product", "Amount");
        System.out.println("                        ------------------------------------------------------------------------------------");
        float sumBill = 0;
        for (int i = 0; i < carts.size(); i++) {
            Cart c = carts.get(i);
            System.out.printf("%-25s%-25s%-25s%-25s%-25d\n", " ", c.getNameProduct(),c.getLocalBrand(), Product.covertPrice(c.getPriceProduct()), c.getAmountProduct());
            sumBill += c.getPriceProduct()* c.getAmountProduct();
        }
        System.out.printf("%-50s%-25s%-25s\n"," ","TotalBill:", Product.covertPrice(sumBill));
    }

    public static Map<String, List<Cart>> cartOfUser() {
        return carts.stream().collect(Collectors.groupingBy(Cart::getUser));
    }


}
