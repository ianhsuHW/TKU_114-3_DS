public class SearchEfficiencyReport {
    public static void main(String[] args) {
        int[] sizes = {16, 128, 1024};

        System.out.println("=== 搜尋效率分析（比較次數）===");
        System.out.println("資料內容：第 i 筆為 (i + 1) * 5，已升冪排序");
        System.out.println();

        System.out.printf(
            "%6s %10s %10s %10s %10s %10s %10s%n",
            "n", "循序-第一", "二分-第一",
            "循序-最後", "二分-最後",
            "循序-不存在", "二分-不存在"
        );

        for (int size : sizes) {
            int[] data = buildSortedData(size);

            int firstValue = data[0];
            int lastValue = data[data.length - 1];
            int missingValue = -1;

            System.out.printf(
                "%6d %10d %10d %10d %10d %10d %10d%n",
                size,
                sequentialChecks(data, firstValue),
                binaryChecks(data, firstValue),
                sequentialChecks(data, lastValue),
                binaryChecks(data, lastValue),
                sequentialChecks(data, missingValue),
                binaryChecks(data, missingValue)
            );
        }

        System.out.println();
        printObservation(sizes);
    }

    public static int[] buildSortedData(int size) {
        int[] data = new int[size];
        for (int index = 0; index < size; index++) {
            data[index] = (index + 1) * 5;
        }
        return data;
    }

    public static int sequentialChecks(int[] data, int target) {
        int checks = 0;

        for (int index = 0; index < data.length; index++) {
            checks++;
            if (data[index] == target) {
                return checks;
            }
        }
        return checks;
    }

    public static int binaryChecks(int[] data, int target) {
        int checks = 0;
        int low = 0;
        int high = data.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            checks++;

            if (data[mid] == target) {
                return checks;
            }
            if (data[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return checks;
    }

    public static void printObservation(int[] sizes) {
        System.out.println("=== 觀察結果 ===");

        System.out.println(
            "1. 搜尋第一筆時 Sequential Search 只需 1 次比較，"
            + "反而比 Binary Search 少。"
        );
        System.out.println(
            "2. 搜尋最後一筆或不存在的資料時，"
            + "Sequential Search 必須比較完整個陣列。"
        );

        for (int size : sizes) {
            int[] data = buildSortedData(size);
            int sequentialWorst = sequentialChecks(data, -1);
            int binaryWorst = binaryChecks(data, -1);

            System.out.printf(
                "   n=%d：最差情況 循序 %d 次，二分 %d 次，相差 %d 次%n",
                size, sequentialWorst, binaryWorst,
                sequentialWorst - binaryWorst
            );
        }

        System.out.println(
            "3. 資料量每變成 8 倍，Sequential Search 的最差比較次數"
            + "也大約變成 8 倍，屬於 O(n)。"
        );
        System.out.println(
            "4. Binary Search 每次刪除一半範圍，"
            + "資料量加倍才多約 1 次比較，屬於 O(log n)。"
        );
        System.out.println(
            "5. 以比較次數而非執行毫秒數判斷，"
            + "才不會受電腦效能與其他程式干擾。"
        );
    }
}
