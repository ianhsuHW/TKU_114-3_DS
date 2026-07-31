import java.util.Arrays;

public class MergeSortPractice {
    public static void main(String[] args) {
        int[] values = {41, 12, 35, 8, 27, 19, 50, 3};

        System.out.println("=== Merge Sort 追蹤 ===");
        System.out.println("排序前：" + Arrays.toString(values));
        System.out.println();

        mergeSort(values);

        System.out.println();
        System.out.println("排序後：" + Arrays.toString(values));

        System.out.println();
        System.out.println("=== 邊界測試 ===");
        boundaryTest(new int[0]);
        boundaryTest(new int[] {7});
        boundaryTest(new int[] {1, 2, 3, 4, 5});
        boundaryTest(new int[] {5, 4, 3, 2, 1});
    }

    public static void boundaryTest(int[] values) {
        System.out.print("排序前：" + Arrays.toString(values));
        mergeSortQuiet(values);
        System.out.println("　排序後：" + Arrays.toString(values));
    }

    public static void mergeSort(int[] values) {
        if (values == null || values.length < 2) {
            return;
        }
        int[] temp = new int[values.length];
        mergeSort(values, temp, 0, values.length - 1, 0);
    }

    public static void mergeSortQuiet(int[] values) {
        if (values == null || values.length < 2) {
            return;
        }
        int[] temp = new int[values.length];
        mergeSortQuiet(values, temp, 0, values.length - 1);
    }

    private static void mergeSort(
        int[] values,
        int[] temp,
        int left,
        int right,
        int depth
    ) {
        // base case：區間只剩 0 或 1 筆
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        String indent = "  ".repeat(depth);
        System.out.printf(
            "%s拆分 %d..%d -> %d..%d 與 %d..%d%n",
            indent, left, right, left, mid, mid + 1, right
        );

        mergeSort(values, temp, left, mid, depth + 1);
        mergeSort(values, temp, mid + 1, right, depth + 1);
        merge(values, temp, left, mid, right);

        int[] range = Arrays.copyOfRange(values, left, right + 1);
        System.out.printf(
            "%s合併 %d..%d：%s%n",
            indent, left, right, Arrays.toString(range)
        );
    }

    private static void mergeSortQuiet(
        int[] values, int[] temp, int left, int right
    ) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSortQuiet(values, temp, left, mid);
        mergeSortQuiet(values, temp, mid + 1, right);
        merge(values, temp, left, mid, right);
    }

    private static void merge(
        int[] values, int[] temp, int left, int mid, int right
    ) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        int tempIndex = left;

        while (leftIndex <= mid && rightIndex <= right) {
            if (values[leftIndex] <= values[rightIndex]) {
                temp[tempIndex] = values[leftIndex];
                leftIndex++;
            } else {
                temp[tempIndex] = values[rightIndex];
                rightIndex++;
            }
            tempIndex++;
        }

        while (leftIndex <= mid) {
            temp[tempIndex] = values[leftIndex];
            tempIndex++;
            leftIndex++;
        }

        while (rightIndex <= right) {
            temp[tempIndex] = values[rightIndex];
            tempIndex++;
            rightIndex++;
        }

        // 合併結果必須複製回原陣列，且只複製目前區間
        for (int index = left; index <= right; index++) {
            values[index] = temp[index];
        }
    }
}
