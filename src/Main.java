import account.Account;
import account.ManagerAccount;
import carts.Cart;
import carts.ManagerCart;
import products.ManagerProduct;
import products.Product;

import java.util.*;

import static carts.ManagerCart.readFromFileCart;
import static carts.ManagerCart.writeToFileCart;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ManagerAccount managerAccount = new ManagerAccount();
    static ManagerProduct managerProduct = new ManagerProduct();
    static ManagerCart managerCart = new ManagerCart();
    static Product product = new Product();
    static List<Cart> listStr = new ArrayList<>();

    public static void main(String[] args) {
        managerAccount.readFromFileAccount();
        managerProduct.readFromFileProduct();
//        readFromFileCart();

        Account account = new Account();


        while (true) {
            System.out.println("Welcome MMP Store");
            System.out.println("1.Login");
            System.out.println("2.Register");
            System.out.println("3.Exit");
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
                    System.out.println("User name:");
                    String name = sc.nextLine();
                    System.out.println("Password");
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
                    System.exit(0);
                    break;
            }

        }


    }

    public static void actionUser(Account account) {
        System.out.println(account);
        if (account.getRole().equals("admin")) {
            while (true) {

                System.out.println("Manager Product");
                managerProduct.showProductManager();
                System.out.println("1.Add product");
                System.out.println("2.Edit product by Id");
                System.out.println("3.Delete product by Id");
                System.out.println("4.Exit");
                int choice = Integer.parseInt(sc.nextLine());

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
                        System.exit(0);
                        break;

                }
            }
        } else if (account.getRole().equals("user")) {

            System.out.println("Menu product");
            managerProduct.showProductCustom();
            System.out.println("1.Buy product");
            System.out.println("2.History by product");
            System.out.println("3.Comment");
            System.out.println("4.Exit");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Enter Id");
                    String idBuy = sc.nextLine();
                    System.out.println("Enter Amount");
                    int amountBuy = Integer.parseInt(sc.nextLine());
                    Cart cart = ManagerProduct.buyProduct(idBuy, amountBuy, account.getUserName());
                    if (cart != null) {
                        listStr.add(cart);
                        writeToFileCart(listStr);
                    }
                    break;
                case 2:
                    readFromFileCart();
                   Map<String, List<Cart>> mapList = ManagerCart.cartOfUser();
                   List<Cart> listCart = mapList.get(account.getUserName());
                   if(!(listCart.isEmpty())){
                       ManagerCart.showCartUser(listCart);
                   }else{
                       System.out.println("You not by any thing");
                   }

                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    break;
            }

        }
    }



}