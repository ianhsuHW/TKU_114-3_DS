import java.util.Arrays;

public class MergeArrayPractice {
    public static void main(String[] args) {
        // 兩個長度不同的已排序陣列
        int[] left = {-8, -3, 4, 4, 15, 27, 33};
        int[] right = {-5, 4, 10, 40};

        System.out.println("=== 一般情況 ===");
        report(left, right);

        System.out.println();
        System.out.println("=== 其中一個陣列為空 ===");
        report(left, new int[0]);
        report(new int[0], right);

        System.out.println();
        System.out.println("=== 兩個都為空 ===");
        report(new int[0], new int[0]);

        System.out.println();
        System.out.println("=== 重複值與負數 ===");
        report(new int[] {-2, -2, 0}, new int[] {-2, 0, 0});
    }

    public static void report(int[] left, int[] right) {
        int[] result = merge(left, right);

        System.out.println("左：" + Arrays.toString(left)
            + "　右：" + Arrays.toString(right));
        System.out.println("合併：" + Arrays.toString(result)
            + "　長度 " + result.length
            + "（應為 " + (left.length + right.length) + "）");
    }

    // 使用三個索引：leftIndex、rightIndex、resultIndex
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            // 使用 <= 讓相同值時先取左側，重複值不會遺失
            if (left[leftIndex] <= right[rightIndex]) {
                result[resultIndex] = left[leftIndex];
                leftIndex++;
            } else {
                result[resultIndex] = right[rightIndex];
                rightIndex++;
            }
            resultIndex++;
        }

        // 兩個收尾迴圈都不可省略，否則剩餘資料會遺失
        while (leftIndex < left.length) {
            result[resultIndex] = left[leftIndex];
            resultIndex++;
            leftIndex++;
        }

        while (rightIndex < right.length) {
            result[resultIndex] = right[rightIndex];
            resultIndex++;
            rightIndex++;
        }

        return result;
    }
}
