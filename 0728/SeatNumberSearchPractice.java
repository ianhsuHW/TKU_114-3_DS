import java.util.Arrays;
import java.util.Scanner;

public class SeatNumberSearchPractice {
    public static void main(String[] args) {
        // 已排序的座位編號，共 12 筆
        int[] seats = {
            101, 105, 108, 112, 120, 125,
            130, 136, 142, 150, 158, 165
        };

        System.out.println("座位編號（已排序）：" + Arrays.toString(seats));
        System.out.println();

        System.out.println("=== 固定測試 ===");
        search(seats, 101);
        search(seats, 165);
        search(seats, 125);
        search(seats, 999);

        System.out.println();
        System.out.println("=== 鍵盤輸入查詢 ===");
        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入座位編號：");

        if (scanner.hasNextInt()) {
            int target = scanner.nextInt();
            search(seats, target);
        } else {
            System.out.println();
            System.out.println("（沒有輸入資料，略過此次查詢）");
        }

        scanner.close();
    }

    public static void search(int[] values, int target) {
        System.out.println("搜尋 " + target + "：");
        int index = binarySearch(values, target);

        if (index == -1) {
            System.out.println("  結果：找不到座位 " + target);
        } else {
            System.out.println("  結果：索引 " + index);
        }
    }

    public static int binarySearch(int[] values, int target) {
        int low = 0;
        int high = values.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            System.out.printf(
                "  low=%d, mid=%d, high=%d, value=%d，剩餘 %d 筆%n",
                low, mid, high, values[mid], high - low + 1
            );

            if (values[mid] == target) {
                return mid;
            }
            if (target < values[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
