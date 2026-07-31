import java.util.Arrays;
import java.util.Scanner;

public class ProductIdSearchPractice {
    public static void main(String[] args) {
        // 未排序的商品編號，共 9 筆
        int[] productIds = {
            4501, 1203, 3318, 2205, 5140, 1876, 3902, 2764, 4088
        };

        System.out.println("商品編號（未排序）：" +
            Arrays.toString(productIds));
        System.out.println();

        System.out.println("=== 固定測試 ===");
        search(productIds, productIds[0]);
        search(productIds, productIds[productIds.length - 1]);
        search(productIds, 9999);

        System.out.println();
        System.out.println("=== 鍵盤輸入查詢 ===");
        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入要搜尋的商品編號：");

        if (scanner.hasNextInt()) {
            int target = scanner.nextInt();
            search(productIds, target);
        } else {
            System.out.println();
            System.out.println("（沒有輸入資料，略過此次查詢）");
        }

        scanner.close();
    }

    public static void search(int[] values, int target) {
        int comparisons = 0;
        int foundIndex = -1;

        for (int index = 0; index < values.length; index++) {
            comparisons++;
            if (values[index] == target) {
                foundIndex = index;
                break;
            }
        }

        if (foundIndex == -1) {
            System.out.printf(
                "編號 %d：找不到這筆商品，比較 %d 次%n",
                target, comparisons
            );
        } else {
            // 只有在確定找到時才使用索引存取陣列
            System.out.printf(
                "編號 %d：索引 %d，資料 %d，比較 %d 次%n",
                target, foundIndex, values[foundIndex], comparisons
            );
        }
    }
}
