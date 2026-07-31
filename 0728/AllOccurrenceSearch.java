import java.util.Arrays;

public class AllOccurrenceSearch {
    public static void main(String[] args) {
        int[] data = {14, 7, 23, 7, 41, 7, 9, 23};

        System.out.println("資料：" + Arrays.toString(data));
        System.out.println();

        searchAll(data, 7);
        searchAll(data, 23);
        searchAll(data, 14);
        searchAll(data, 99);
        searchAll(new int[0], 5);
    }

    public static void searchAll(int[] data, int target) {
        if (data == null || data.length == 0) {
            System.out.printf(
                "搜尋 %d：資料為空，比較 0 次%n",
                target
            );
            return;
        }

        int[] positions = new int[data.length];
        int found = 0;
        int comparisons = 0;

        for (int index = 0; index < data.length; index++) {
            comparisons++;
            if (data[index] == target) {
                positions[found] = index;
                found++;
            }
        }

        if (found == 0) {
            System.out.printf(
                "搜尋 %d：找不到這筆資料，比較 %d 次%n",
                target, comparisons
            );
            return;
        }

        int[] result = Arrays.copyOf(positions, found);
        System.out.printf(
            "搜尋 %d：索引 %s，出現 %d 次，比較 %d 次%n",
            target, Arrays.toString(result), found, comparisons
        );
    }
}
