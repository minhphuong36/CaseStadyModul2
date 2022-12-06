import java.util.Scanner;
import java.util.regex.Pattern;

public class test {
    public static String check() {
        Scanner sc = new Scanner(System.in);
        String pass = "";
        while (true) {
            System.out.println("input pass");
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
                System.out.println("Not ok");
            }

        }
        return pass;
    }

    public static void main(String[] args) {
        String pass1 = check();
        System.out.println("password " + pass1);
    }
}
