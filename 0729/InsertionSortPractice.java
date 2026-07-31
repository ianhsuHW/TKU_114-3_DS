import java.util.Arrays;

public class InsertionSortPractice {
    public static void main(String[] args) {
        int[] values = {30, 10, 20, 50, 40, 5};

        System.out.println("=== Insertion Sort 移動追蹤 ===");
        int[] counts = insertionSort(values, true);
        System.out.printf(
            "資料比較 %d 次，元素右移 %d 次%n",
            counts[0], counts[1]
        );

        System.out.println();
        System.out.println("=== 不同排列的比較 ===");

        int[] sorted = {5, 10, 20, 30, 40, 50};
        int[] reversed = {50, 40, 30, 20, 10, 5};
        int[] original = {30, 10, 20, 50, 40, 5};

        int[] sortedCounts = insertionSort(sorted.clone(), false);
        int[] reversedCounts = insertionSort(reversed.clone(), false);
        int[] originalCounts = insertionSort(original.clone(), false);

        System.out.printf(
            "已排序資料：比較 %d 次，右移 %d 次%n",
            sortedCounts[0], sortedCounts[1]
        );
        System.out.printf(
            "原始資料：  比較 %d 次，右移 %d 次%n",
            originalCounts[0], originalCounts[1]
        );
        System.out.printf(
            "反向排序：  比較 %d 次，右移 %d 次%n",
            reversedCounts[0], reversedCounts[1]
        );

        System.out.println();
        System.out.printf(
            "結論：反向排序資料的移動次數最多（%d 次），"
            + "因為每個 key 都必須移到最前面；%n",
            reversedCounts[1]
        );
        System.out.printf(
            "      已排序資料最少（%d 次），while 條件第一次就失敗。%n",
            sortedCounts[1]
        );
    }

    public static int[] insertionSort(int[] values, boolean trace) {
        int comparisons = 0;
        int moves = 0;

        if (trace) {
            System.out.println("開始：" + Arrays.toString(values));
        }

        for (int index = 1; index < values.length; index++) {
            // key 必須先保存，右移時原位置會被覆蓋
            int key = values[index];
            int position = index - 1;

            while (position >= 0) {
                comparisons++;
                if (values[position] <= key) {
                    break;
                }
                values[position + 1] = values[position];
                moves++;
                position--;
            }

            values[position + 1] = key;

            if (trace) {
                System.out.printf(
                    "key=%d，插入位置=%d，結果=%s%n",
                    key, position + 1, Arrays.toString(values)
                );
            }
        }

        return new int[] {comparisons, moves};
    }
}
