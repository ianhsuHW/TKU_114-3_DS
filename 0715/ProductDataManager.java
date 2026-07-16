import java.util.Scanner;

public class ProductDataManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] records = {
            "Keyboard,890,12",
            "Mouse,490,20",
            "Monitor,5200,5",
            "USB Cable,250,30",
            "Headset,1290,8"
        };

        String[] names = new String[records.length];
        int[] prices = new int[records.length];
        int[] stocks = new int[records.length];

        System.out.println("=== 解析商品資料 ===");
        for (int i = 0; i < records.length; i++) {
            String[] parts = records[i].split(",");

            if (parts.length == 3) {
                try {
                    names[i] = parts[0].trim();
                    prices[i] = Integer.parseInt(parts[1].trim());
                    stocks[i] = Integer.parseInt(parts[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("第 " + (i + 1) + " 筆資料格式錯誤");
                }
            } else {
                System.out.println("第 " + (i + 1) + " 筆欄位數錯誤");
            }
        }

        System.out.println("\n=== 商品表格 ===");
        System.out.println("商品名稱\t\t價格\t庫存");
        for (int i = 0; i < names.length; i++) {
            if (names[i] != null) {
                System.out.println(names[i] + "\t\t" + prices[i] + "\t" + stocks[i]);
            }
        }

        System.out.print("\n請輸入搜尋商品名稱：");
        String keyword = sc.nextLine();

        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            if (names[i] != null && names[i].equalsIgnoreCase(keyword)) {
                System.out.println("找到：" + names[i] + " - 價格：" + prices[i] + "，庫存：" + stocks[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("找不到商品");
        }

        System.out.println("\n=== 低庫存商品 ===");
        for (int i = 0; i < names.length; i++) {
            if (names[i] != null && stocks[i] < 10) {
                System.out.println(names[i] + " - 庫存：" + stocks[i]);
            }
        }

        System.out.println("\n=== 庫存總價值 ===");
        long total = 0;
        for (int i = 0; i < names.length; i++) {
            if (names[i] != null) {
                total += (long) prices[i] * stocks[i];
            }
        }
        System.out.println("總價值：" + total);

        System.out.print("\n請輸入新商品資料（格式：名稱,價格,庫存）：");
        String newRecord = sc.nextLine();
        String[] newParts = newRecord.split(",");

        if (newParts.length == 3) {
            try {
                String newName = newParts[0].trim();
                int newPrice = Integer.parseInt(newParts[1].trim());
                int newStock = Integer.parseInt(newParts[2].trim());

                if (newPrice > 0 && newStock >= 0) {
                    System.out.println("商品新增成功：" + newName);
                } else {
                    System.out.println("價格與庫存格式錯誤");
                }
            } catch (NumberFormatException e) {
                System.out.println("價格或庫存必須為整數");
            }
        } else {
            System.out.println("欄位數不足或格式錯誤");
        }

        sc.close();
    }
}
