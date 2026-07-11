import java.util.Scanner;

public class AtmMethodHomework {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        int balance = 1000;

        while (option != 0) {
            printMenu();
            System.out.print("輸入選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    showBalance(balance);
                    break;
                case 2:
                    int deposit = readPositiveAmount(sc, "存款");
                    balance = deposit(balance, deposit);
                    showBalance(balance);
                    break;
                case 3:
                    int withdraw = readPositiveAmount(sc, "提款");
                    while (withdraw > balance) {
                        System.out.print("餘額不足");
                        withdraw = readPositiveAmount(sc, "提款");
                    }
                    balance = withdraw(balance, withdraw);
                    showBalance(balance);
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

    public static void printMenu() {
        System.out.println("1. 餘額");
        System.out.println("2. 存款");
        System.out.println("3. 提款");
        System.out.println("0. 離開");
    }

    public static void showBalance(int balance) {
        System.out.println("餘額: " + balance);
    }

    public static int readPositiveAmount(Scanner sc, String type) {
        System.out.print(type + "金額：");
        int amount = sc.nextInt();
        while (amount <= 0) {
            System.out.print(type + "大於 0");
            amount = sc.nextInt();
        }
        return amount;
    }

    public static int deposit(int balance, int amount) {
        return balance + amount;
    }

    public static int withdraw(int balance, int amount) {
        return balance - amount;
    }
}
