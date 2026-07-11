import java.util.Scanner;

public class DrinkOrderSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        int blackTeaCount = 0;
        int greenTeaCount = 0;
        int milkTeaCount = 0;
        int coffeeCount = 0;
        int totalItems = 0;
        int totalAmount = 0;

        while (option != 0) {
            printMenu();
            System.out.print("選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    int qty1 = readValidQuantity(sc);
                    blackTeaCount += qty1;
                    totalItems += qty1;
                    totalAmount += calculateSubtotal(30, qty1);
                    break;
                case 2:
                    int qty2 = readValidQuantity(sc);
                    greenTeaCount += qty2;
                    totalItems += qty2;
                    totalAmount += calculateSubtotal(35, qty2);
                    break;
                case 3:
                    int qty3 = readValidQuantity(sc);
                    milkTeaCount += qty3;
                    totalItems += qty3;
                    totalAmount += calculateSubtotal(45, qty3);
                    break;
                case 4:
                    int qty4 = readValidQuantity(sc);
                    coffeeCount += qty4;
                    totalItems += qty4;
                    totalAmount += calculateSubtotal(50, qty4);
                    break;
                case 0:
                    int finalAmount = calculateDiscountedTotal(totalAmount);
                    printReceipt(blackTeaCount, greenTeaCount, milkTeaCount, coffeeCount, totalItems, totalAmount, finalAmount);
                    break;
                default:
                    System.out.println("無效");
            }
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Milk tea   $45");
        System.out.println("4. Coffee     $50");
        System.out.println("0. Checkout");
    }

    public static int readValidQuantity(Scanner sc) {
        int quantity;
        while (true) {
            System.out.print("數量：");
            quantity = sc.nextInt();
            if (quantity > 0) {
                return quantity;
            }
            System.out.println("大於 0");
        }
    }

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    public static int calculateDiscountedTotal(int totalAmount) {
        if (totalAmount >= 300) {
            return (int) (totalAmount * 0.9);
        }
        return totalAmount;
    }

    public static void printReceipt(int blackTeaCount, int greenTeaCount, int milkTeaCount, int coffeeCount, int totalItems, int totalAmount, int finalAmount) {
        System.out.println("=== Receipt ===");
        System.out.println("Black tea: " + blackTeaCount);
        System.out.println("Green tea: " + greenTeaCount);
        System.out.println("Milk tea: " + milkTeaCount);
        System.out.println("Coffee: " + coffeeCount);
        System.out.println("Total items: " + totalItems);
        System.out.println("Original amount: " + totalAmount);
        System.out.println("Discount: " + (totalAmount >= 300 ? "Yes" : "No"));
        System.out.println("Final amount: " + finalAmount);
    }
}
