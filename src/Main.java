import account.Account;
import account.ManagerAccount;
import carts.Cart;
import carts.ManagerCart;
import products.ManagerProduct;
import products.Product;
import sort.SortUpPrice;

import java.util.*;

import static carts.ManagerCart.readFromFileCart;
import static carts.ManagerCart.writeToFileCart;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ManagerAccount managerAccount = new ManagerAccount();
    static ManagerProduct managerProduct = new ManagerProduct();
    static ManagerCart managerCart = new ManagerCart();
    static Product product = new Product();

    public static void main(String[] args) {
        managerAccount.readFromFileAccount();
        managerProduct.readFromFileProduct();
        readFromFileCart();
        Account account = new Account();


        while (true) {
            System.out.println("Welcome MMP Store");
            System.out.println("1.Login");
            System.out.println("2.Register");
            System.out.println("3.Log Out");
            int choice;
            do {
                try {
                    System.out.print("Enter number from 1 to 3! ");
                    choice = Integer.parseInt(sc.nextLine());
                    break;
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Enter the number, please!");
                }
            } while (true);


            switch (choice) {
                case 1:
                    System.out.print("User name:");
                    String name = sc.nextLine();
                    System.out.print("Password :");
                    String pass = sc.nextLine();
                    account = managerAccount.checkAccount(name, pass);
                    if (Objects.nonNull(account)) {
                        actionUser(account);
                    }
                   break;
                case 2:
                    Account newAcc = managerAccount.createAccount();
                    managerAccount.addAccounts(newAcc);
                   break;
                case 3:
                    return;

            }

        }


    }

    public static void actionUser(Account account) {

        if (account.getRole().equals("admin")) {
            while (true) {

                System.out.printf("%-25s%-25s\n"," ","MANAGER PRODUCT");
                managerProduct.showProductManager();
                System.out.println("1.Add product");
                System.out.println("2.Edit product by Id");
                System.out.println("3.Delete product by Id");
                System.out.println("4.Log Out");
                int choice;
                do {
                    try {
                        System.out.print("Enter number from 1 to 4! ");
                        choice = Integer.parseInt(sc.nextLine());
                        break;
                    } catch (InputMismatchException | NumberFormatException e) {
                        System.err.println("Choice the number, please!");
                    }
                } while (true);

                switch (choice) {
                    case 1:
                        Product product = managerProduct.createProduct();
                        managerProduct.addProduct(product);
                        break;
                    case 2:
                        System.out.println("Enter Id");
                        String idAdd = sc.nextLine();
                        managerProduct.editProduct(idAdd);
                        break;
                    case 3:
                        System.out.println("Enter Id");
                        String idDelete = sc.nextLine();
                        managerProduct.deleteProduct(idDelete);
                        break;
                    case 4:
                       return;

                }
            }
        } else if (account.getRole().equals("user")) {


            while (true) {

                System.out.println("1.Buy product");
                System.out.println("2.History bought product");
                System.out.println("3.Sort by price product");
                System.out.println("4.Log Out");

                int choice;
                do {
                    try {
                        System.out.print("Choice number from 1 to 4! ");
                        choice = Integer.parseInt(sc.nextLine());
                        break;
                    } catch (InputMismatchException | NumberFormatException e) {
                        System.err.println("Enter the number, please!");
                    }
                } while (true);
                switch (choice) {
                    case 1:
                        System.out.printf("%-25s%-25s\n"," ","MENU PRODUCT");
                        managerProduct.showProductCustom();
                        System.out.println("Enter Id");
                        String idBuy = sc.nextLine();
                        System.out.println("Enter Amount");
                        int amountBuy = Integer.parseInt(sc.nextLine());
                        Cart cart = ManagerProduct.buyProduct(idBuy, amountBuy, account.getUserName());
                        if (cart != null) {
                            writeToFileCart(cart);
                        }
                        break;
                    case 2:
                        System.out.println("History bought product");
                        Map<String, List<Cart>> mapList = ManagerCart.cartOfUser();
                        List<Cart> listCart = mapList.get(account.getUserName());
                        if (listCart != null && !(listCart.isEmpty())) {
                            ManagerCart.showCartUser(listCart);
                        } else {
                            System.err.println("You can not buy any thing");
                        }

                        break;
                    case 3:

                        System.out.println("Sort by price product");
                        Collections.sort(ManagerProduct.products, new SortUpPrice());
                        ManagerProduct.showProductCustom();
                        break;
                    case 4:
                        return;

                }

            }
        }
    }



}