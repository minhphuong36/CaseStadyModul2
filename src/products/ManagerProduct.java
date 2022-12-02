package products;

import carts.Cart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerProduct {
    Scanner sc = new Scanner(System.in);
    static List<Product> products = new ArrayList<>();
    Product product = new Product();

    public static void writeToFileProduct(List<Product> products) {
        try {
            FileWriter fw = new FileWriter("dataproduct.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Product o : products) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();

        } catch (Exception e) {

        }
    }

    public List<Product> readFromFileProduct() {
        try {
            FileReader fr = new FileReader("dataproduct.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(",");
                String id = txt[0];
                String name = txt[1];
                String localBrand = txt[2];
                double price = Double.parseDouble(txt[3]);
                int amount = Integer.parseInt(txt[4]);
                products.add(new Product(id, name, localBrand, price, amount));

            }
        } catch (Exception e) {
        }
        return products;
    }


    public void showProductManager() {
        System.out.printf("%-25s%-25s%-25s%-25s%-25s%-25s\n", " ", "Id product", "Name product", "LocalBrand", "Price product", "Amount");
        System.out.println("                        -------------------------------------------------------------------------------------------------");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.printf("%-25s%-25s%-25s%-25s%-25f%-25d\n", " ", p.getId(), p.getName(), p.getLocalBrand(), p.getPrice(), p.getAmount());
        }
    }

    public void showProductCustom() {
        System.out.printf("%-25s%-25s%-25s%-25s%-25s\n", " ", "Id product", "Name product", "LocalBrand", "Price product");
        System.out.println("                        --------------------------------------------------------------------------------------------------");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.printf("%-25s%-25s%-25s%-25s%-25f\n", " ", p.getId(), p.getName(), p.getLocalBrand(), p.getPrice());
        }
    }


    public void addProduct(Product product) {
        products.add(product);
        writeToFileProduct(products);
    }

    public Product createProduct() {
        System.out.println("Enter Id");
        String id = sc.nextLine();

        System.out.println("Enter Name");
        String name = sc.nextLine();

        System.out.println("Enter LocalBrand");
        String localBrand = sc.nextLine();

        System.out.println("Enter Price");
        double price = Double.parseDouble(sc.nextLine());
        System.out.println("Enter Amount");
        int amount = Integer.parseInt(sc.nextLine());

        return new Product(id, name, localBrand, price, amount);
    }

    public static int findIndexById(String id) {
        for (int i = 0; i < products.size(); i++) {
            if (id.equals(products.get(i).getId())) {
                return i;
            }
        }
        return -1;

    }


    public void editProduct(String id) {
        int index = findIndexById(id);
        if (index != -1) {
            Product p = products.get(index);
            System.out.println("Enter newId");
            String newId = sc.nextLine();
            if ("".equals(newId)) {
                p.setId(p.getId());
            } else {
                p.setId(newId);
            }
            System.out.println("Enter Name");
            String newName = sc.nextLine();
            if ("".equals(newName)) {
                p.setName(p.getName());
            } else {
                p.setId(newName);
            }

            System.out.println("Enter LocalBrand");
            String newLocalbrand = sc.nextLine();
            if ("".equals(newLocalbrand)) {
                p.setLocalBrand(p.getLocalBrand());
            } else {
                p.setLocalBrand(newLocalbrand);
            }

            System.out.println("Enter Price");
            String newPrice = sc.nextLine();
            if ("".equals(newPrice)) {
                p.setPrice(p.getPrice());
            } else {
                double newPrice1 = Double.parseDouble(newPrice);
                p.setPrice(newPrice1);
            }

            System.out.println("Enter Amount");
            String newAmount = sc.nextLine();
            if ("".equals(newAmount)) {
                p.setAmount(p.getAmount());
            } else {
                int newAmount1 = Integer.parseInt(newAmount);
                p.setAmount(newAmount1);
            }

        } else {
            System.out.println("Id does not exist");
        }

        writeToFileProduct(products);
    }

    public void deleteProduct(String id) {
        int index = findIndexById(id);
        if (index != -1) {
            products.remove(index);
        } else {
            System.out.println("Id is not exist");
        }
    }



    public static Cart buyProduct(String id, int amount, String userName) {
        int index = findIndexById(id);
        Product p = products.get(index);
        Cart cart = null;
        if (index != -1) {
            if (p.getAmount() < amount) {
                System.out.println("Amount is not enough!");
            } else {
                p.setAmount(p.getAmount() - amount);
                System.out.printf("%-25s%-25s%-25s\n", "", "Name Product", " SumBill");
                System.out.printf("%-25s%-25s%-25f\n", "", p.getName(), amount * p.getPrice());
                cart = new Cart(userName, p.getName(), p.getPrice(), amount, p.getLocalBrand());
            }
        } else {
            System.out.println("Id is not exist");
        }
        writeToFileProduct(products);
        return cart;

    }




}
