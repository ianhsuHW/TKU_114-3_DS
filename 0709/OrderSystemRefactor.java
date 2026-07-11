import java.util.Scanner;

public class OrderSystemRefactor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        int totalItems = 0;
        int totalAmount = 0;

        while (option != 0) {
            printOrderMenu();
            System.out.print("輸入選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    int qty1 = readQuantity(sc);
                    totalItems += qty1;
                    totalAmount += qty1 * 30;
                    break;
                case 2:
                    int qty2 = readQuantity(sc);
                    totalItems += qty2;
                    totalAmount += qty2 * 35;
                    break;
                case 3:
                    int qty3 = readQuantity(sc);
                    totalItems += qty3;
                    totalAmount += qty3 * 50;
                    break;
                case 0:
                    printReceipt(totalItems, totalAmount);
                    break;
                default:
                    System.out.println("無效");
            }
        }

        sc.close();
    }

    public static void printOrderMenu() {
        System.out.println("=== Order Menu ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Coffee     $50");
        System.out.println("0. Checkout");
    }

    public static int readQuantity(Scanner sc) {
        System.out.print("輸入數量：");
        int quantity = sc.nextInt();
        while (quantity <= 0) {
            System.out.print("數量大於 0");
            quantity = sc.nextInt();
        }
        return quantity;
    }

    public static void printReceipt(int totalItems, int totalAmount) {
        System.out.println("=== Receipt ===");
        System.out.println("Total items: " + totalItems);
        System.out.println("Total amount: " + totalAmount);
    }
}
