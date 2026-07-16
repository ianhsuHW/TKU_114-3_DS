import java.util.Scanner;

public class ProductSearchSystem {
    static String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};

    public static void showAll() {
        System.out.println("\n=== 全部商品 ===");
        for (String name : names) {
            System.out.println(name);
        }
    }

    public static void searchExact(Scanner sc) {
        System.out.print("請輸入商品名稱：");
        String keyword = sc.nextLine().trim();

        boolean found = false;
        for (String name : names) {
            if (name.equalsIgnoreCase(keyword)) {
                System.out.println("找到：" + name);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("找不到商品");
        }
    }

    public static void searchPartial(Scanner sc) {
        System.out.print("請輸入搜尋關鍵字：");
        String keyword = sc.nextLine().trim().toLowerCase();

        System.out.println("搜尋結果：");
        boolean found = false;

        for (String name : names) {
            if (name.toLowerCase().contains(keyword)) {
                System.out.println(name);
                found = true;
            }
        }

        if (!found) {
            System.out.println("找不到相符商品");
        }
    }

    public static void showLongest() {
        System.out.println("\n=== 最長商品名稱 ===");
        String longest = "";
        for (String name : names) {
            if (name.length() > longest.length()) {
                longest = name;
            }
        }
        System.out.println(longest);
    }

    public static void showPosition(Scanner sc) {
        System.out.print("請輸入商品名稱：");
        String name = sc.nextLine().trim();
        System.out.print("請輸入搜尋關鍵字：");
        String keyword = sc.nextLine().trim();

        for (String product : names) {
            if (product.equalsIgnoreCase(name)) {
                int index = product.toLowerCase().indexOf(keyword.toLowerCase());
                if (index >= 0) {
                    System.out.println("第一次出現位置：" + index);
                } else {
                    System.out.println("找不到關鍵字");
                }
                return;
            }
        }
        System.out.println("找不到商品");
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== 商品搜尋系統 ===");
            System.out.println("1. 顯示全部商品");
            System.out.println("2. 完整名稱搜尋");
            System.out.println("3. 部分名稱搜尋");
            System.out.println("4. 顯示最長商品名稱");
            System.out.println("5. 顯示關鍵字位置");
            System.out.println("6. 結束");
            System.out.print("選擇：");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    showAll();
                    break;
                case 2:
                    searchExact(sc);
                    break;
                case 3:
                    searchPartial(sc);
                    break;
                case 4:
                    showLongest();
                    break;
                case 5:
                    showPosition(sc);
                    break;
                case 6:
                    System.out.println("系統結束");
                    break;
                default:
                    System.out.println("選擇錯誤");
            }
        } while (choice != 6);

        sc.close();
    }

    public static void main(String[] args) {
        menu();
    }
}
