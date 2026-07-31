import java.util.Arrays;

public class SortingExperiment {
    public static void main(String[] args) {
        int[] sortedData = {10, 20, 30, 40, 50, 60, 70, 80};
        int[] reversedData = {80, 70, 60, 50, 40, 30, 20, 10};
        int[] shuffledData = {50, 20, 80, 10, 70, 30, 60, 40};

        runGroup("已排序", sortedData);
        runGroup("反向排序", reversedData);
        runGroup("隨機排列", shuffledData);

        System.out.println();
        printConclusion(sortedData, reversedData, shuffledData);
    }

    public static void runGroup(String label, int[] original) {
        int[] selectionData = original.clone();
        int[] insertionData = original.clone();

        int[] selectionCounts = selectionSort(selectionData);
        int[] insertionCounts = insertionSort(insertionData);

        System.out.println("=== " + label + " ===");
        System.out.println("原始：" + Arrays.toString(original));
        System.out.println("結果：" + Arrays.toString(selectionData));
        System.out.printf(
            "Selection：比較 %d 次，交換 %d 次%n",
            selectionCounts[0], selectionCounts[1]
        );
        System.out.printf(
            "Insertion：比較 %d 次，移動 %d 次%n",
            insertionCounts[0], insertionCounts[1]
        );
        System.out.println();
    }

    public static int[] selectionSort(int[] values) {
        int comparisons = 0;
        int swaps = 0;

        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;

            for (int index = start + 1; index < values.length; index++) {
                comparisons++;
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                }
            }

            if (minIndex != start) {
                int temp = values[start];
                values[start] = values[minIndex];
                values[minIndex] = temp;
                swaps++;
            }
        }
        return new int[] {comparisons, swaps};
    }

    public static int[] insertionSort(int[] values) {
        int comparisons = 0;
        int moves = 0;

        for (int index = 1; index < values.length; index++) {
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
        }
        return new int[] {comparisons, moves};
    }

    public static void printConclusion(
        int[] sortedData,
        int[] reversedData,
        int[] shuffledData
    ) {
        int[] sortedInsertion = insertionSort(sortedData.clone());
        int[] reversedInsertion = insertionSort(reversedData.clone());
        int[] shuffledInsertion = insertionSort(shuffledData.clone());

        int[] sortedSelection = selectionSort(sortedData.clone());
        int[] reversedSelection = selectionSort(reversedData.clone());

        System.out.println("=== 觀察結論 ===");

        System.out.printf(
            "1. Selection Sort 的比較次數固定為 %d 次，"
            + "三組資料完全相同，與原始順序無關。%n",
            sortedSelection[0]
        );
        System.out.printf(
            "2. 已排序資料時 Selection Sort 仍交換 %d 次，"
            + "反向資料則交換 %d 次。%n",
            sortedSelection[1], reversedSelection[1]
        );
        System.out.printf(
            "3. Insertion Sort 的比較次數受原始順序影響："
            + "已排序 %d 次、隨機 %d 次、反向 %d 次。%n",
            sortedInsertion[0],
            shuffledInsertion[0],
            reversedInsertion[0]
        );
        System.out.printf(
            "4. 已排序資料時 Insertion Sort 只移動 %d 次，"
            + "反向資料需移動 %d 次，是它的最差情況。%n",
            sortedInsertion[1], reversedInsertion[1]
        );
        System.out.println(
            "5. 三組實驗都從相同原始資料 clone() 出副本，"
            + "確保比較條件一致。"
        );
        System.out.println(
            "6. 結論：接近有序的資料適合 Insertion Sort；"
            + "若想控制交換次數則可考慮 Selection Sort。"
        );
    }
}
