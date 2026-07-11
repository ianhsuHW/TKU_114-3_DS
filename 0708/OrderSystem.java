import java.util.Scanner;

public class OrderSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        int totalItems = 0;
        int totalAmount = 0;

        while (option != 0) {
            System.out.println("1. Black tea  $30");
            System.out.println("2. Green tea  $35");
            System.out.println("3. Coffee     $50");
            System.out.println("0. Checkout");
            System.out.print("輸入選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.print("輸入數量：");
                    int qty1 = sc.nextInt();
                    while (qty1 <= 0) {
                        System.out.print("數量必須大於 0，重新輸入：");
                        qty1 = sc.nextInt();
                    }
                    int subtotal1 = qty1 * 30;
                    totalItems += qty1;
                    totalAmount += subtotal1;
                    System.out.println("Subtotal: " + subtotal1);
                    break;
                case 2:
                    System.out.print("輸入數量：");
                    int qty2 = sc.nextInt();
                    while (qty2 <= 0) {
                        System.out.print("數量必須大於 0，重新輸入：");
                        qty2 = sc.nextInt();
                    }
                    int subtotal2 = qty2 * 35;
                    totalItems += qty2;
                    totalAmount += subtotal2;
                    System.out.println("Subtotal: " + subtotal2);
                    break;
                case 3:
                    System.out.print("輸入數量：");
                    int qty3 = sc.nextInt();
                    while (qty3 <= 0) {
                        System.out.print("數量必須大於 0，重新輸入：");
                        qty3 = sc.nextInt();
                    }
                    int subtotal3 = qty3 * 50;
                    totalItems += qty3;
                    totalAmount += subtotal3;
                    System.out.println("Subtotal: " + subtotal3);
                    break;
                case 0:
                    System.out.println("=== Receipt ===");
                    System.out.println("Total items: " + totalItems);
                    System.out.println("Total amount: " + totalAmount);
                    break;
                default:
                    System.out.println("Unknown");
            }
        }

        sc.close();
    }
}
