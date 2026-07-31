public class AlgorithmComparisonReport {
    public static void main(String[] args) {
        int[] sizes = {16, 128, 1024};
        String[] labels = {"已排序", "反向排序", "固定亂序"};

        System.out.println("=== 排序演算法比較報告（資料比較次數）===");
        System.out.println("說明：每個演算法都使用同一份原始資料的獨立副本。");
        System.out.println();

        System.out.printf(
            "%6s %10s %12s %12s %12s%n",
            "n", "資料排列", "Selection", "Insertion", "Merge"
        );

        for (int size : sizes) {
            for (int mode = 0; mode < labels.length; mode++) {
                int[] original = buildData(size, mode);

                int selection = selectionSortComparisons(original.clone());
                int insertion = insertionSortComparisons(original.clone());
                int merge = mergeSortComparisons(original.clone());

                System.out.printf(
                    "%6d %10s %12d %12d %12d%n",
                    size, labels[mode], selection, insertion, merge
                );
            }
            System.out.println();
        }

        printObservation(sizes);
    }

    // ---------- 建立測試資料 ----------

    public static int[] buildData(int size, int mode) {
        if (mode == 0) {
            return buildSorted(size);
        }
        if (mode == 1) {
            return buildReversed(size);
        }
        return buildShuffled(size);
    }

    public static int[] buildSorted(int size) {
        int[] data = new int[size];
        for (int index = 0; index < size; index++) {
            data[index] = (index + 1) * 2;
        }
        return data;
    }

    public static int[] buildReversed(int size) {
        int[] data = new int[size];
        for (int index = 0; index < size; index++) {
            data[index] = (size - index) * 2;
        }
        return data;
    }

    // 使用固定公式產生可重現的亂序資料，不依賴隨機亂數。
    public static int[] buildShuffled(int size) {
        int[] data = buildSorted(size);
        int seed = 7919;

        for (int index = size - 1; index > 0; index--) {
            seed = (seed * 31 + 17) % 100003;
            int target = seed % (index + 1);

            int temp = data[index];
            data[index] = data[target];
            data[target] = temp;
        }
        return data;
    }

    // ---------- Selection Sort ----------

    public static int selectionSortComparisons(int[] values) {
        int comparisons = 0;

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
            }
        }
        return comparisons;
    }

    // ---------- Insertion Sort ----------

    public static int insertionSortComparisons(int[] values) {
        int comparisons = 0;

        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;

            while (position >= 0) {
                comparisons++;
                if (values[position] <= key) {
                    break;
                }
                values[position + 1] = values[position];
                position--;
            }
            values[position + 1] = key;
        }
        return comparisons;
    }

    // ---------- Merge Sort ----------

    public static int mergeSortComparisons(int[] values) {
        if (values == null || values.length < 2) {
            return 0;
        }

        int[] counter = new int[1];
        int[] temp = new int[values.length];
        mergeSort(values, temp, 0, values.length - 1, counter);
        return counter[0];
    }

    private static void mergeSort(
        int[] values,
        int[] temp,
        int left,
        int right,
        int[] counter
    ) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(values, temp, left, mid, counter);
        mergeSort(values, temp, mid + 1, right, counter);
        merge(values, temp, left, mid, right, counter);
    }

    private static void merge(
        int[] values,
        int[] temp,
        int left,
        int mid,
        int right,
        int[] counter
    ) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        int tempIndex = left;

        while (leftIndex <= mid && rightIndex <= right) {
            counter[0]++;
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

        for (int index = left; index <= right; index++) {
            values[index] = temp[index];
        }
    }

    // ---------- 觀察結論（由程式計算，不寫死）----------

    public static void printObservation(int[] sizes) {
        System.out.println("=== 觀察結論 ===");

        System.out.println(
            "1. Selection Sort 的比較次數只由資料量決定："
        );
        for (int size : sizes) {
            int sorted = selectionSortComparisons(buildData(size, 0).clone());
            int reversed = selectionSortComparisons(buildData(size, 1).clone());
            int shuffled = selectionSortComparisons(buildData(size, 2).clone());

            System.out.printf(
                "   n=%d：已排序 %d、反向 %d、亂序 %d（三者相同 = %s）%n",
                size, sorted, reversed, shuffled,
                (sorted == reversed && reversed == shuffled)
            );
        }

        System.out.println(
            "2. Insertion Sort 在已排序資料是最佳情況，反向是最差情況："
        );
        for (int size : sizes) {
            int sorted = insertionSortComparisons(buildData(size, 0).clone());
            int reversed = insertionSortComparisons(buildData(size, 1).clone());

            System.out.printf(
                "   n=%d：已排序 %d 次（約 n-1 = %d），反向 %d 次%n",
                size, sorted, size - 1, reversed
            );
        }

        System.out.println(
            "3. Merge Sort 的比較次數在三種排列下差異很小，效率穩定："
        );
        for (int size : sizes) {
            int sorted = mergeSortComparisons(buildData(size, 0).clone());
            int reversed = mergeSortComparisons(buildData(size, 1).clone());
            int shuffled = mergeSortComparisons(buildData(size, 2).clone());

            System.out.printf(
                "   n=%d：已排序 %d、反向 %d、亂序 %d%n",
                size, sorted, reversed, shuffled
            );
        }

        System.out.println(
            "4. 資料量放大時，O(n²) 與 O(n log n) 的差距迅速擴大："
        );
        for (int size : sizes) {
            int quadratic = selectionSortComparisons(buildData(size, 2).clone());
            int linearithmic = mergeSortComparisons(buildData(size, 2).clone());

            System.out.printf(
                "   n=%d：Selection %d 次 vs Merge %d 次，相差約 %d 倍%n",
                size, quadratic, linearithmic,
                quadratic / linearithmic
            );
        }

        System.out.println(
            "5. 本報告以比較次數作為判斷依據，不使用單次執行毫秒數，"
        );
        System.out.println(
            "   因為毫秒數會受電腦效能、JIT 暖機及背景程式影響。"
        );
        System.out.println(
            "6. 結論：小量或接近有序資料可用 Insertion Sort；"
            + "大量一般資料應使用 Merge Sort；"
        );
        System.out.println(
            "   Selection Sort 的比較次數無法因資料有序而減少，"
            + "只在需要控制交換次數時才有優勢。"
        );
    }
}
