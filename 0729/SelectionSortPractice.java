import java.util.Arrays;

public class SelectionSortPractice {
    public static void main(String[] args) {
        int[] values = {42, 18, 35, 7, 29, 14};

        System.out.println("=== Selection Sort 每輪追蹤 ===");
        int[] counts = selectionSort(values);

        System.out.println();
        System.out.printf(
            "比較次數：%d，實際交換次數：%d%n",
            counts[0], counts[1]
        );

        System.out.println();
        System.out.println("=== 邊界測試 ===");

        int[] empty = new int[0];
        int[] emptyCounts = selectionSort(empty);
        System.out.printf(
            "空陣列：%s，比較 %d 次，交換 %d 次%n",
            Arrays.toString(empty), emptyCounts[0], emptyCounts[1]
        );

        int[] single = {99};
        int[] singleCounts = selectionSort(single);
        System.out.printf(
            "單一元素：%s，比較 %d 次，交換 %d 次%n",
            Arrays.toString(single), singleCounts[0], singleCounts[1]
        );
    }

    public static int[] selectionSort(int[] values) {
        int comparisons = 0;
        int swaps = 0;

        if (values.length > 1) {
            System.out.println("開始：" + Arrays.toString(values));
        }

        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;

            for (int index = start + 1; index < values.length; index++) {
                comparisons++;
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                }
            }

            // 只有真正需要換位時才交換，統計才會反映實際動作
            if (minIndex != start) {
                int temp = values[start];
                values[start] = values[minIndex];
                values[minIndex] = temp;
                swaps++;
            }

            System.out.printf(
                "start=%d，選中索引=%d，結果=%s%n",
                start, minIndex, Arrays.toString(values)
            );
        }

        return new int[] {comparisons, swaps};
    }
}
