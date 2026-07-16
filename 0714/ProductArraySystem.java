import java.util.Scanner;

public class ProductArraySystem {
    static String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
    static int[] prices = {890, 490, 5200, 250, 1290};
    static int[] stocks = {12, 20, 5, 30, 8};

    static int totalBought = 0;
    static int totalRestocked = 0;
    static int totalTransactions = 0;

    public static void showAllProducts() {
        System.out.println("\n=== 全部商品 ===");
        for (int i = 0; i < names.length; i++) {
            System.out.println((i + 1) + ". " + names[i] + " - 價格：" + prices[i] + "，庫存：" + stocks[i]);
        }
    }

    public static void queryProduct(Scanner sc) {
        System.out.print("請輸入商品編號：");
        int index = sc.nextInt() - 1;

        if (index >= 0 && index < names.length) {
            System.out.println("商品：" + names[index]);
            System.out.println("價格：" + prices[index]);
            System.out.println("庫存：" + stocks[index]);
        } else {
            System.out.println("編號錯誤");
        }
    }

    public static void buyProduct(Scanner sc) {
        System.out.print("請輸入商品編號：");
        int index = sc.nextInt() - 1;

        if (index < 0 || index >= names.length) {
            System.out.println("編號錯誤");
            return;
        }

        System.out.print("購買數量：");
        int quantity = sc.nextInt();

        if (quantity <= 0 || quantity > stocks[index]) {
            System.out.println("數量錯誤或庫存不足");
        } else {
            stocks[index] -= quantity;
            totalBought += quantity;
            totalTransactions++;
            System.out.println("購買成功。剩餘庫存：" + stocks[index]);
        }
    }

    public static void restockProduct(Scanner sc) {
        System.out.print("請輸入商品編號：");
        int index = sc.nextInt() - 1;

        if (index < 0 || index >= names.length) {
            System.out.println("編號錯誤");
            return;
        }

        System.out.print("補貨數量：");
        int quantity = sc.nextInt();

        if (quantity <= 0) {
            System.out.println("數量必須大於0");
        } else {
            stocks[index] += quantity;
            totalRestocked += quantity;
            totalTransactions++;
            System.out.println("補貨成功。目前庫存：" + stocks[index]);
        }
    }

    public static void showLowStock() {
        System.out.println("\n=== 低庫存商品 ===");
        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < 10) {
                System.out.println(names[i] + " - 庫存：" + stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("沒有低庫存商品");
        }
    }

    public static void showTotalValue() {
        System.out.println("\n=== 庫存總價值 ===");
        long total = 0;
        for (int i = 0; i < names.length; i++) {
            total += (long) prices[i] * stocks[i];
        }
        System.out.println("總價值：" + total);
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== 商品管理系統 ===");
            System.out.println("1. 顯示全部商品");
            System.out.println("2. 依商品編號查詢");
            System.out.println("3. 購買商品");
            System.out.println("4. 補充商品庫存");
            System.out.println("5. 顯示低庫存商品");
            System.out.println("6. 顯示全部庫存總價值");
            System.out.println("7. 結束");
            System.out.print("選擇：");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    showAllProducts();
                    break;
                case 2:
                    queryProduct(sc);
                    break;
                case 3:
                    buyProduct(sc);
                    break;
                case 4:
                    restockProduct(sc);
                    break;
                case 5:
                    showLowStock();
                    break;
                case 6:
                    showTotalValue();
                    break;
                case 7:
                    System.out.println("\n=== 操作摘要 ===");
                    System.out.println("總購買件數：" + totalBought);
                    System.out.println("總補貨件數：" + totalRestocked);
                    System.out.println("總交易次數：" + totalTransactions);
                    break;
                default:
                    System.out.println("選擇錯誤");
            }
        } while (choice != 7);

        sc.close();
    }

    public static void main(String[] args) {
        menu();
    }
}
