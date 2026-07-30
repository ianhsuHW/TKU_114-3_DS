public class Q08_AlgorithmDecision {
    public static void main(String[] args) {
        int[] data = new int[64];
        for (int index = 0; index < data.length; index++) {
            data[index] = (index + 1) * 3;
        }

        System.out.println("已排序：" +
            isSortedAscending(data));
        System.out.println("循序比較次數：" +
            sequentialChecks(data, 192));
        System.out.println("二分比較次數：" +
            binaryChecks(data, 192));
        System.out.println("建議：" +
            chooseSearch(true, data.length, 5));
    }

    public static boolean isSortedAscending(int[] data) {
        if (data == null || data.length < 2) {
            return true;
        }

        for (int index = 1; index < data.length; index++) {
            if (data[index - 1] > data[index]) {
                return false;
            }
        }
        return true;
    }

    public static int sequentialChecks(int[] data, int target) {
        if (data == null) {
            return 0;
        }

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
        if (data == null) {
            return 0;
        }

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

    public static String chooseSearch(
        boolean sorted,
        int dataSize,
        int expectedSearches
    ) {
        if (!sorted) {
            return "SEQUENTIAL";
        }
        if (dataSize >= 32 && expectedSearches >= 2) {
            return "BINARY";
        }
        return "SEQUENTIAL";
    }
}
