package carts;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ManagerCart {
    static List<Cart> carts = new ArrayList<>();



    public static void writeToFileCart(List<Cart> listStr) {
        try {
            FileWriter fw = new FileWriter("cart.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Cart o : listStr) {
                bw.write(o.toString());
                bw.newLine();
            }
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

    public void showCart() {
        System.out.printf("%-25s%-25s%-25s%-25s%-25s\n", " ", "Name Product", "Price Product", "Amount");
        System.out.println("                        -------------------------------------------------------------------------------------------------");
        for (int i = 0; i < carts.size(); i++) {
            Cart c = carts.get(i);
            System.out.printf("%-25s%-25s%-25s%-25f%-25d\n", " ", c.getNameProduct(), c.getPriceProduct(), c.getAmountProduct());
        }
    }

    public static void showCartUser(List<Cart> carts) {
        System.out.printf("%-25s%-25s%-25s%-25s%-25s\n", " ", "Name Product", "Price Product", "Amount","localBrand");
        System.out.println("                        -------------------------------------------------------------------------------------------------");
        for (int i = 0; i < carts.size(); i++) {
            Cart c = carts.get(i);
            System.out.printf("%-25s%-25s%-25f%-25d%-25s\n", " ", c.getNameProduct(), c.getPriceProduct(), c.getAmountProduct(),c.getLocalBrand());
        }
    }

    public static Map<String, List<Cart>> cartOfUser() {
        return carts.stream().collect(Collectors.groupingBy(Cart::getUser));
    }


}