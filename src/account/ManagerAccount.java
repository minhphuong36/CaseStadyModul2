package account;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class ManagerAccount {
    static Scanner sc = new Scanner(System.in);

    static List<Account> accounts = new ArrayList<>();
    Account account = new Account();

    public void writeToFileAccount(List<Account> accounts) {
        try {
            FileWriter fw = new FileWriter("data.txt");
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
                String phone = txt[3];
                accounts.add(new Account(userName, passWord, role, phone));

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


    public static String createPassWord() {

        String pass = "";

        while (true) {
            System.out.println("Enter passWord (have uppercase, lowercase letters, special symbols, at least 6 characters)");
            pass = sc.nextLine();
            Pattern pUp = Pattern.compile("^.*[A-Z]+.*$");
            Pattern pDown = Pattern.compile("^.*[a-z]+.*$");
            Pattern pDigit = Pattern.compile("^.*[0-9]+.*$");
            Pattern pSpecial = Pattern.compile("^.*[#?!@$%^&*-]+.*$");
            Pattern pLength = Pattern.compile("^.{6,}.*$");
            if (pUp.matcher(pass).find() && pDown.matcher(pass).find() && pDigit.matcher(pass).find() &&
                    pSpecial.matcher(pass).find() && pLength.matcher(pass).find()) {
                break;
            } else {
                System.err.println("                          The pass word is not regular!");
            }

        }
        return pass;
    }

    public static String createPhoneNumber() {
        String phone;
        while (true) {
            System.out.println("Enter phone number (Ex regular phone: xxx-xxx-xxxx/ yyy.yyy.yyyy/ zzz zzz zzzz/ (nnn)-nnn-nnnn)");
            phone = sc.nextLine();
            Pattern p1 = Pattern.compile("^[0-9]{10}$");
            Pattern p2 = Pattern.compile("^[0-9]{3}-[0-9]{3}-[0-9]{4}$");
            Pattern p3 = Pattern.compile("^[0-9]{3}.[0-9]{3}.[0-9]{4}$");
            Pattern p4 = Pattern.compile("^[0-9]{3} [0-9]{3} [0-9]{4}$");
            Pattern p5 = Pattern.compile("^\\([0-9]{3}\\)-[0-9]{3}-[0-9]{4}$");
            if (p1.matcher(phone).find() || p2.matcher(phone).find() || p3.matcher(phone).find() ||
                    p4.matcher(phone).find() || p5.matcher(phone).find()) {
                break;
            } else {
                System.err.println("                          The phone number is not regular");
            }

        }
        return phone;

    }

    public static boolean isUserName(String userName) {
        for (int i = 0; i < accounts.size(); i++) {
            if (userName.equals(accounts.get(i).getUserName())) {
                return true;
            }
        }
        return false;
    }

    public static String createUser() {
        String Name = "";
        while (true) {
            System.out.println("Enter user:");
            Name = sc.nextLine();
            if (isUserName(Name)) {
                System.out.println("ko hơp le");
            } else {
                break;
            }
        }
        return Name;
    }

    public Account createAccount() {
        String name = createUser();
        String pass = createPassWord();
        String phone = createPhoneNumber();
        return new Account(name, pass, "user", phone);
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
            System.out.println("Logged in Successfully!");
            return accounts.get(index);
        } else {
            System.err.println("User or password is not exactly!");
        }
        return null;
    }


}

