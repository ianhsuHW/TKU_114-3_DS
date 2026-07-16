import java.util.Scanner;

public class ProductManagementSystem {
    static Product[] products = new Product[10];
    static int count = 0;

    static {
        products[0] = new Product("Keyboard", 890, 12);
        products[1] = new Product("Mouse", 490, 20);
        products[2] = new Product("Monitor", 5200, 5);
        products[3] = new Product("USB Cable", 250, 30);
        products[4] = new Product("Headset", 1290, 8);
        count = 5;
    }

    public static void showAll() {
        System.out.println("\n=== 全部商品 ===");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + products[i]);
        }
    }

    public static void findByName(Scanner sc) {
        System.out.print("請輸入商品名稱：");
        String name = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (products[i].getName().equalsIgnoreCase(name.trim())) {
                System.out.println("找到：" + products[i]);
                return;
            }
        }
        System.out.println("找不到商品");
    }

    public static void addProduct(Scanner sc) {
        if (count >= 10) {
            System.out.println("陣列已滿，無法新增");
            return;
        }

        System.out.print("商品名稱：");
        String name = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (products[i].getName().equalsIgnoreCase(name.trim())) {
                System.out.println("商品已存在");
                return;
            }
        }

        System.out.print("價格：");
        int price = sc.nextInt();

        System.out.print("庫存：");
        int stock = sc.nextInt();
        sc.nextLine();

        products[count] = new Product(name, price, stock);
        count++;
        System.out.println("新增成功");
    }

    public static void sellProduct(Scanner sc) {
        System.out.print("請輸入商品名稱：");
        String name = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (products[i].getName().equalsIgnoreCase(name.trim())) {
                System.out.print("銷售數量：");
                int quantity = sc.nextInt();
                sc.nextLine();

                if (products[i].sell(quantity)) {
                    System.out.println("銷售成功");
                } else {
                    System.out.println("數量錯誤或庫存不足");
                }
                return;
            }
        }
        System.out.println("找不到商品");
    }

    public static void restockProduct(Scanner sc) {
        System.out.print("請輸入商品名稱：");
        String name = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (products[i].getName().equalsIgnoreCase(name.trim())) {
                System.out.print("補貨數量：");
                int quantity = sc.nextInt();
                sc.nextLine();

                if (products[i].restock(quantity)) {
                    System.out.println("補貨成功");
                } else {
                    System.out.println("數量錯誤");
                }
                return;
            }
        }
        System.out.println("找不到商品");
    }

    public static void setPrice(Scanner sc) {
        System.out.print("請輸入商品名稱：");
        String name = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (products[i].getName().equalsIgnoreCase(name.trim())) {
                System.out.print("新價格：");
                int price = sc.nextInt();
                sc.nextLine();

                if (products[i].setPrice(price)) {
                    System.out.println("價格修改成功");
                } else {
                    System.out.println("價格錯誤");
                }
                return;
            }
        }
        System.out.println("找不到商品");
    }

    public static void showLowStock() {
        System.out.println("\n=== 低庫存商品 ===");
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (products[i].isLowStock()) {
                System.out.println(products[i]);
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
        for (int i = 0; i < count; i++) {
            total += products[i].getInventoryValue();
        }
        System.out.println("總價值：" + total);
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== 商品管理系統 ===");
            System.out.println("1. 顯示全部商品");
            System.out.println("2. 依名稱搜尋");
            System.out.println("3. 新增商品");
            System.out.println("4. 出售商品");
            System.out.println("5. 補充庫存");
            System.out.println("6. 修改商品價格");
            System.out.println("7. 顯示低庫存商品");
            System.out.println("8. 顯示全部庫存總價值");
            System.out.println("9. 結束並顯示操作摘要");
            System.out.print("選擇：");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    showAll();
                    break;
                case 2:
                    findByName(sc);
                    break;
                case 3:
                    addProduct(sc);
                    break;
                case 4:
                    sellProduct(sc);
                    break;
                case 5:
                    restockProduct(sc);
                    break;
                case 6:
                    setPrice(sc);
                    break;
                case 7:
                    showLowStock();
                    break;
                case 8:
                    showTotalValue();
                    break;
                case 9:
                    System.out.println("\n=== 操作摘要 ===");
                    System.out.println("目前商品數：" + count);
                    System.out.println("系統結束");
                    break;
                default:
                    System.out.println("選擇錯誤");
            }
        } while (choice != 9);

        sc.close();
    }

    public static void main(String[] args) {
        menu();
    }
}
