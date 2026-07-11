import java.util.Scanner;

public class AtmSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int balance = 1000;
        int depositCount = 0;
        int withdrawCount = 0;
        int totalDeposit = 0;
        int totalWithdraw = 0;
        int option = -1;

        while (option != 0) {
            printMenu();
            System.out.print("選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    printBalance(balance);
                    break;
                case 2:
                    int deposit = readPositiveAmount(sc, "存款：");
                    balance = deposit(balance, deposit);
                    depositCount++;
                    totalDeposit += deposit;
                    printBalance(balance);
                    break;
                case 3:
                    int withdraw = readPositiveAmount(sc, "提款：");
                    while (withdraw > balance) {
                        System.out.println("餘額不足");
                        withdraw = readPositiveAmount(sc, "提款：");
                    }
                    balance = withdraw(balance, withdraw);
                    withdrawCount++;
                    totalWithdraw += withdraw;
                    printBalance(balance);
                    break;
                case 4:
                    printSummary(balance, depositCount, withdrawCount, totalDeposit, totalWithdraw);
                    break;
                case 0:
                    printSummary(balance, depositCount, withdrawCount, totalDeposit, totalWithdraw);
                    break;
                default:
                    System.out.println("無效");
            }
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Check balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Show summary");
        System.out.println("0. Exit");
    }

    public static int readPositiveAmount(Scanner sc, String message) {
        int amount;
        while (true) {
            System.out.print(message);
            amount = sc.nextInt();
            if (amount > 0) {
                return amount;
            }
            System.out.println("大於 0");
        }
    }

    public static int deposit(int balance, int amount) {
        return balance + amount;
    }

    public static int withdraw(int balance, int amount) {
        return balance - amount;
    }

    public static void printBalance(int balance) {
        System.out.println("Balance: " + balance);
    }

    public static void printSummary(int balance, int depositCount, int withdrawCount, int totalDeposit, int totalWithdraw) {
        System.out.println("=== Summary ===");
        System.out.println("Final balance: " + balance);
        System.out.println("Deposit count: " + depositCount);
        System.out.println("Withdraw count: " + withdrawCount);
        System.out.println("Total deposit: " + totalDeposit);
        System.out.println("Total withdraw: " + totalWithdraw);
    }
}
