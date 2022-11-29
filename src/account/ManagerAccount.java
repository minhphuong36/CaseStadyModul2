package account;

import products.Product;

import java.io.*;
import java.util.*;

public class ManagerAccount {
    Scanner sc = new Scanner(System.in);

    List<Account> accounts = new ArrayList<>();
    Account account = new Account();

    public void writeToFileAccount(List<Account> accounts) {
        try {
            FileWriter fw = new FileWriter("data.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Account o : accounts) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();

        } catch (Exception e) {

        }
    }

    public List<Account> readFromFileAccount() {
        try {
            FileReader fr = new FileReader("data.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(",");
                String userName = txt[0];
                String passWord = txt[1];
                String role = txt[2];
                accounts.add(new Account(userName, passWord, role));

            }
        } catch (Exception e) {
        }
        return accounts;
    }


    public void showAccount() {
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i).toString());
        }
    }


    public void addAccounts(Account account) {
        accounts.add(account);
        writeToFileAccount(accounts);
    }

    public Account createAccount() {
        System.out.println("Nhập tên đăng nhập");
        String name = sc.nextLine();

        System.out.println("Nhập mật khẩu");
        String pass = sc.nextLine();

        System.out.println("Nhập số điện thoại");
        int phone = Integer.parseInt(sc.nextLine());
        return new Account(name, pass, "user");


    }

    public int findIndexByUserPass(String userName, String passWord) {

        for (int i = 0; i < accounts.size(); i++) {
            if (userName.equals(accounts.get(i).getUserName()) && passWord.equals(accounts.get(i).getPassWord())) {
                return i;
            }
        }
        return -1;
    }

    public Account checkAccount(String userName, String passWord) {
        int index = findIndexByUserPass(userName, passWord);
        if (index != -1) {
            System.out.println("Đăng nhập thành công");
        } else {
            System.out.println("Tài khoản hoặc mat khẩu không đúng");

        }

        return accounts.get(index);
    }
}

