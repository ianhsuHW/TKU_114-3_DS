import java.util.Scanner;

public class InputValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("輸入分數0-100：");
        int score = sc.nextInt();

        while (score < 0 || score > 100) {
            System.out.print("分數不合法，重新輸入0-100：");
            score = sc.nextInt();
        }

        System.out.println("Score: " + score);
        sc.close();
    }
}
