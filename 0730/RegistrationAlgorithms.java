import java.util.ArrayList;

public class RegistrationAlgorithms {

    // ---------- Merge Sort：依報名編號升冪 ----------

    public static void mergeSortById(Registration[] data) {
        if (data == null || data.length < 2) {
            return;
        }

        Registration[] temp = new Registration[data.length];
        mergeSortById(data, temp, 0, data.length - 1);
    }

    private static void mergeSortById(
        Registration[] data,
        Registration[] temp,
        int left,
        int right
    ) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSortById(data, temp, left, mid);
        mergeSortById(data, temp, mid + 1, right);
        merge(data, temp, left, mid, right);
    }

    private static void merge(
        Registration[] data,
        Registration[] temp,
        int left,
        int mid,
        int right
    ) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        int tempIndex = left;

        while (leftIndex <= mid && rightIndex <= right) {
            // 排序鍵值與 Binary Search 的搜尋鍵值必須一致，
            // 因此兩邊都統一轉成小寫再比較。
            String leftId = data[leftIndex].getId().toLowerCase();
            String rightId = data[rightIndex].getId().toLowerCase();

            if (leftId.compareTo(rightId) <= 0) {
                temp[tempIndex] = data[leftIndex];
                leftIndex++;
            } else {
                temp[tempIndex] = data[rightIndex];
                rightIndex++;
            }
            tempIndex++;
        }

        while (leftIndex <= mid) {
            temp[tempIndex] = data[leftIndex];
            tempIndex++;
            leftIndex++;
        }

        while (rightIndex <= right) {
            temp[tempIndex] = data[rightIndex];
            tempIndex++;
            rightIndex++;
        }

        for (int index = left; index <= right; index++) {
            data[index] = temp[index];
        }
    }

    // ---------- Binary Search：依編號（需已排序）----------

    public static Registration binarySearchById(
        Registration[] sorted,
        String id
    ) {
        if (sorted == null || id == null) {
            return null;
        }

        String target = id.trim().toLowerCase();
        if (target.isEmpty()) {
            return null;
        }

        int low = 0;
        int high = sorted.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            String currentId = sorted[mid].getId().toLowerCase();
            int comparison = target.compareTo(currentId);

            if (comparison == 0) {
                return sorted[mid];
            } else if (comparison < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return null;
    }

    // ---------- Sequential Search：依姓名 ----------

    public static ArrayList<Registration> findByName(
        ArrayList<Registration> data,
        String name
    ) {
        ArrayList<Registration> results = new ArrayList<>();

        if (data == null || name == null) {
            return results;
        }

        String target = name.trim().toLowerCase();
        if (target.isEmpty()) {
            return results;
        }

        for (Registration registration : data) {
            if (registration.getName().toLowerCase().contains(target)) {
                results.add(registration);
            }
        }
        return results;
    }

    // ---------- 工具 ----------

    public static Registration[] toArray(ArrayList<Registration> data) {
        Registration[] result = new Registration[data.size()];

        for (int index = 0; index < data.size(); index++) {
            result[index] = data.get(index);
        }
        return result;
    }
}
