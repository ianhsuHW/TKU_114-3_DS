import java.util.Arrays;

public class InventorySearchPractice {
    public static void main(String[] args) {
        // 至少 12 筆未排序庫存編號
        int[] inventoryIds = {
            5140, 1203, 4501, 2205, 3318, 1876,
            3902, 2764, 4088, 1055, 4790, 2431
        };

        System.out.println("排序前：" + Arrays.toString(inventoryIds));

        mergeSort(inventoryIds);
        System.out.println("排序後：" + Arrays.toString(inventoryIds));

        System.out.println();
        System.out.println("=== Binary Search 查詢 ===");

        int firstId = inventoryIds[0];
        int lastId = inventoryIds[inventoryIds.length - 1];
        int middleId = inventoryIds[inventoryIds.length / 2];

        search(inventoryIds, firstId);
        search(inventoryIds, lastId);
        search(inventoryIds, middleId);
        search(inventoryIds, 9999);

        System.out.println();
        System.out.println("說明：排序與搜尋都以「庫存編號」為鍵值，方向皆為升冪，");
        System.out.println("      因此 Binary Search 的前提條件成立。");
    }

    public static void search(int[] values, int target) {
        int index = binarySearch(values, target);

        if (index == -1) {
            System.out.printf("編號 %d：找不到%n", target);
        } else {
            System.out.printf(
                "編號 %d：索引 %d%n", target, index
            );
        }
    }

    public static int binarySearch(int[] values, int target) {
        int low = 0;
        int high = values.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (values[mid] == target) {
                return mid;
            }
            if (values[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void mergeSort(int[] values) {
        if (values == null || values.length < 2) {
            return;
        }
        int[] temp = new int[values.length];
        mergeSort(values, temp, 0, values.length - 1);
    }

    private static void mergeSort(
        int[] values, int[] temp, int left, int right
    ) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(values, temp, left, mid);
        mergeSort(values, temp, mid + 1, right);
        merge(values, temp, left, mid, right);
    }

    private static void merge(
        int[] values, int[] temp, int left, int mid, int right
    ) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (values[i] <= values[j]) {
                temp[k++] = values[i++];
            } else {
                temp[k++] = values[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = values[i++];
        }
        while (j <= right) {
            temp[k++] = values[j++];
        }
        for (int index = left; index <= right; index++) {
            values[index] = temp[index];
        }
    }
}
