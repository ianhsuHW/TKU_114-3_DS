import java.util.Arrays;

public class RangeSearchSystem {
    public static void main(String[] args) {
        int[] data = {3, 8, 8, 8, 15, 21, 21, 30, 30, 30, 42};

        System.out.println("已排序資料：" + Arrays.toString(data));
        System.out.println();

        report(data, 8);
        report(data, 30);
        report(data, 3);
        report(data, 42);
        report(data, 15);
        report(data, 99);

        System.out.println();
        System.out.println("=== 邊界測試 ===");
        report(new int[0], 8);
        report(new int[] {7}, 7);
        report(new int[] {5, 5, 5, 5}, 5);
    }

    public static void report(int[] data, int target) {
        int[] range = findRange(data, target);
        int count = countOccurrences(data, target);

        System.out.printf(
            "目標 %d：索引範圍 %s，出現 %d 次%n",
            target, Arrays.toString(range), count
        );
    }

    public static int[] findRange(int[] data, int target) {
        return new int[] {
            findFirst(data, target),
            findLast(data, target)
        };
    }

    public static int countOccurrences(int[] data, int target) {
        int first = findFirst(data, target);
        if (first == -1) {
            return 0;
        }
        return findLast(data, target) - first + 1;
    }

    public static int findFirst(int[] data, int target) {
        if (data == null) {
            return -1;
        }

        int low = 0;
        int high = data.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (data[mid] == target) {
                result = mid;
                high = mid - 1;
            } else if (data[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static int findLast(int[] data, int target) {
        if (data == null) {
            return -1;
        }

        int low = 0;
        int high = data.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (data[mid] == target) {
                result = mid;
                low = mid + 1;
            } else if (data[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }
}
