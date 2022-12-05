public class test {
    public static String covertPrice(double price) {
        String covertPrice = String.format("%,.0f vnd", price);
        return covertPrice;
    }
    public static void main(String[] args) {
        System.out.println(covertPrice(11000010));
    }
}
