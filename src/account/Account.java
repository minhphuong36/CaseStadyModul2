package account;

import products.Product;

import java.util.List;

public  class Account {
    private String userName;
    private String passWord;

    private String role;
    private String phone;

    public Account(){

    }

    public Account(String userName, String passWord, String role,String phone) {
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
        this.phone = phone;
    }

    public String getRole() {

        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return userName +"," + passWord +","+role +","+phone;
    }
}
