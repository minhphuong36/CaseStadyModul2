import account.Account;
import account.ManagerAccount;
import products.ManagerProduct;
import products.Product;

import java.util.Scanner;

public class Main {
   static Scanner sc = new Scanner(System.in);
    static ManagerAccount managerAccount = new ManagerAccount();
    static  ManagerProduct managerProduct = new ManagerProduct();
    static Product product = new Product();

    public static void main(String[] args) {
        managerAccount.readFromFileAccount();
        managerProduct.readFromFileProduct();

        Account account = new Account();


        while (true){
            System.out.println("Welcome MMP Store");
            System.out.println("1.Login");
            System.out.println("2.Register");
            System.out.println("3.Exit");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    System.out.println("Nhập tài khoản");
                    String name = sc.nextLine();
                    System.out.println("Nhập mật khẩu");
                    String pass = sc.nextLine();
                    account = managerAccount.checkAccount(name,pass);
                    actionUser(account, managerProduct);

                    break;
                case 2:
                    Account newAcc = managerAccount.createAccount();
                    managerAccount.addAccounts(newAcc);
                    break;
                default:
                    System.exit(0);
                    break;
            }

        }


    }

    public static void actionUser(Account account, ManagerProduct managerProduct){
        if(account.getRole().equals("admin")){
            while (true){

                System.out.println("Manager Product");
                managerProduct.showProductManager();
                System.out.println("1.Add product");
                System.out.println("2.Edit product by Id");
                System.out.println("3.Delete product by Id");
                System.out.println("4.Show product");
                System.out.println("5.Exit");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice){
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
                        managerProduct.showProductManager();
                        break;
                    case 5:
                        System.exit(0);
                        break;

                }
            }
        } else if (account.getRole().equals("user")) {

            System.out.println("Menu product");
            managerProduct.showProductCustom();
            System.out.println("1.Buy product");
            System.out.println("2.History buy/sale");
            System.out.println("3.Comment");
            System.out.println("4.Exit");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice){
                case 1:
                    System.out.println("Enter Id");
                    String idBuy = sc.nextLine();
                    System.out.println("Enter Amount");
                    int amountBuy = Integer.parseInt(sc.nextLine());
                    ManagerProduct.buyProduct(idBuy,amountBuy);

                    break;
                case 2:
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
