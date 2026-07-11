import java.util.Scanner;

public class AtmMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        int balance = 1000;

        while (option != 0) {
            System.out.println("1. 餘額");
            System.out.println("2. 存款");
            System.out.println("3. 提款");
            System.out.println("0. 離開");
            System.out.print("輸入選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("餘額: " + balance);
                    break;
                case 2:
                    System.out.print("輸入存款金額：");
                    int deposit = sc.nextInt();
                    while (deposit <= 0) {
                        System.out.print("存款大於 0");
                        deposit = sc.nextInt();
                    }
                    balance += deposit;
                    System.out.println("存款成功");
                    System.out.println("餘額: " + balance);
                    break;
                case 3:
                    System.out.print("輸入提款金額：");
                    int withdraw = sc.nextInt();
                    while (withdraw <= 0) {
                        System.out.print("提款大於 0");
                        withdraw = sc.nextInt();
                    }
                    while (withdraw > balance) {
                        System.out.print("餘額不足");
                        withdraw = sc.nextInt();
                    }
                    balance -= withdraw;
                    System.out.println("提款成功");
                    System.out.println("餘額: " + balance);
                    break;
                case 0:
                    System.out.println("離開");
                    break;
                default:
                    System.out.println("無效");
            }
        }

        sc.close();
    }
}
