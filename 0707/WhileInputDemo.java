import java.util.Scanner;

public class WhileInputDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入整數（輸入 0 結束）：");
        int number = sc.nextInt();

        while (number != 0) {
            System.out.println("輸入數字：" + number);
            System.out.print("請輸入整數（輸入 0 結束）：");
            number = sc.nextInt();
        }

        System.out.println("結束");
        sc.close();
    }
}
