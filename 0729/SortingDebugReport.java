import java.util.Arrays;

public class SortingDebugReport {
    public static void main(String[] args) {
        System.out.println("=== 排序程式除錯報告 ===");

        reportBugOne();
        reportBugTwo();
        reportBugThree();

        System.out.println();
        System.out.println("=== 總結 ===");
        System.out.println(
            "1. 內層迴圈範圍錯誤：最小值在最後一格時不會被選到。"
        );
        System.out.println(
            "2. key 未先保存：右移時原值被覆蓋，插入的是錯誤資料。"
        );
        System.out.println(
            "3. 比較方向錯誤：程式可以執行，但排序方向與需求相反。"
        );
        System.out.println(
            "以上三種錯誤都不會產生編譯錯誤，只能靠測試資料與追蹤輸出發現。"
        );
    }

    // ------------------------------------------------------------
    // 錯誤一：內層迴圈範圍錯誤
    // ------------------------------------------------------------

    public static void reportBugOne() {
        // 最小值 1 放在最後一格，才能讓「少掃最後一格」的錯誤顯現。
        int[] data = {5, 4, 3, 2, 1};

        int[] wrongResult = data.clone();
        selectionSortWrongRange(wrongResult);

        int[] fixedResult = data.clone();
        selectionSortFixedRange(fixedResult);

        System.out.println();
        System.out.println("--- 錯誤一：內層迴圈範圍錯誤 ---");
        System.out.println("測試資料：" + Arrays.toString(data));
        System.out.println("修正前：" + Arrays.toString(wrongResult));
        System.out.println("修正後：" + Arrays.toString(fixedResult));
        System.out.println(
            "原因：內層寫成 index < values.length - 1，"
            + "最後一個索引永遠不會被比較到。"
        );
    }

    public static void selectionSortWrongRange(int[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;

            // 錯誤：條件是 index < values.length - 1，
            // 少掃描最後一個元素，最小值在尾端時選不到。
            for (int index = start + 1;
                 index < values.length - 1;
                 index++) {
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                }
            }

            int temp = values[start];
            values[start] = values[minIndex];
            values[minIndex] = temp;
        }
    }

    public static void selectionSortFixedRange(int[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;

            // 修正：內層必須掃描到 values.length - 1（含最後一格）。
            for (int index = start + 1;
                 index < values.length;
                 index++) {
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                }
            }

            int temp = values[start];
            values[start] = values[minIndex];
            values[minIndex] = temp;
        }
    }

    // ------------------------------------------------------------
    // 錯誤二：key 未先保存
    // ------------------------------------------------------------

    public static void reportBugTwo() {
        // 需要實際發生右移，錯誤才會顯現。
        int[] data = {12, 11, 13, 5, 6};

        int[] wrongResult = data.clone();
        insertionSortWrongKey(wrongResult);

        int[] fixedResult = data.clone();
        insertionSortFixedKey(fixedResult);

        System.out.println();
        System.out.println("--- 錯誤二：key 未先保存 ---");
        System.out.println("測試資料：" + Arrays.toString(data));
        System.out.println("修正前：" + Arrays.toString(wrongResult));
        System.out.println("修正後：" + Arrays.toString(fixedResult));
        System.out.println(
            "原因：右移時 values[index] 已被覆蓋，"
            + "之後再讀取它就不是原本要插入的值。"
        );
    }

    public static void insertionSortWrongKey(int[] values) {
        for (int index = 1; index < values.length; index++) {
            int position = index - 1;

            // 錯誤：沒有先把 values[index] 存進 key，
            // 迴圈中每次讀到的 values[index] 都可能已被覆蓋。
            while (position >= 0 && values[position] > values[index]) {
                values[position + 1] = values[position];
                position--;
            }
            values[position + 1] = values[index];
        }
    }

    public static void insertionSortFixedKey(int[] values) {
        for (int index = 1; index < values.length; index++) {
            // 修正：先把要插入的值保存到 key。
            int key = values[index];
            int position = index - 1;

            while (position >= 0 && values[position] > key) {
                values[position + 1] = values[position];
                position--;
            }
            values[position + 1] = key;
        }
    }

    // ------------------------------------------------------------
    // 錯誤三：比較方向錯誤
    // ------------------------------------------------------------

    public static void reportBugThree() {
        // 未排序資料才能看出方向相反。
        int[] data = {29, 10, 14, 37, 13};

        int[] wrongResult = data.clone();
        selectionSortWrongDirection(wrongResult);

        int[] fixedResult = data.clone();
        selectionSortFixedDirection(fixedResult);

        System.out.println();
        System.out.println("--- 錯誤三：比較方向錯誤 ---");
        System.out.println("測試資料：" + Arrays.toString(data));
        System.out.println("需求：升冪排列");
        System.out.println("修正前：" + Arrays.toString(wrongResult));
        System.out.println("修正後：" + Arrays.toString(fixedResult));
        System.out.println(
            "原因：需求為升冪卻使用 > 比較，"
            + "每輪選到的是最大值，結果變成降冪。"
        );
    }

    public static void selectionSortWrongDirection(int[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;

            for (int index = start + 1; index < values.length; index++) {
                // 錯誤：升冪應該用 <，這裡寫成 >，選到的是最大值。
                if (values[index] > values[minIndex]) {
                    minIndex = index;
                }
            }

            int temp = values[start];
            values[start] = values[minIndex];
            values[minIndex] = temp;
        }
    }

    public static void selectionSortFixedDirection(int[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;

            for (int index = start + 1; index < values.length; index++) {
                // 修正：升冪要選最小值，使用 <。
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                }
            }

            int temp = values[start];
            values[start] = values[minIndex];
            values[minIndex] = temp;
        }
    }
}
