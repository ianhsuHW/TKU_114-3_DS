import java.util.ArrayList;

public class RepairAlgorithms {

    // ---------- Merge Sort：依優先等級降冪，相同等級保持登記順序 ----------

    public static void mergeSortByPriority(RepairTask[] tasks) {
        if (tasks == null || tasks.length < 2) {
            return;
        }

        RepairTask[] temp = new RepairTask[tasks.length];
        mergeSortByPriority(tasks, temp, 0, tasks.length - 1);
    }

    private static void mergeSortByPriority(
        RepairTask[] tasks,
        RepairTask[] temp,
        int left,
        int right
    ) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSortByPriority(tasks, temp, left, mid);
        mergeSortByPriority(tasks, temp, mid + 1, right);
        merge(tasks, temp, left, mid, right);
    }

    private static void merge(
        RepairTask[] tasks,
        RepairTask[] temp,
        int left,
        int mid,
        int right
    ) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        int tempIndex = left;

        while (leftIndex <= mid && rightIndex <= right) {
            // 等級相同時先取左側，因此登記順序不會被打亂。
            if (tasks[leftIndex].getPriority() >=
                tasks[rightIndex].getPriority()) {
                temp[tempIndex] = tasks[leftIndex];
                leftIndex++;
            } else {
                temp[tempIndex] = tasks[rightIndex];
                rightIndex++;
            }
            tempIndex++;
        }

        while (leftIndex <= mid) {
            temp[tempIndex] = tasks[leftIndex];
            tempIndex++;
            leftIndex++;
        }

        while (rightIndex <= right) {
            temp[tempIndex] = tasks[rightIndex];
            tempIndex++;
            rightIndex++;
        }

        for (int index = left; index <= right; index++) {
            tasks[index] = temp[index];
        }
    }

    // ---------- Sequential Search ----------

    public static RepairTask findById(
        ArrayList<RepairTask> tasks,
        String id
    ) {
        if (tasks == null || id == null) {
            return null;
        }

        String target = id.trim();
        if (target.isEmpty()) {
            return null;
        }

        for (RepairTask task : tasks) {
            if (task.getId().equalsIgnoreCase(target)) {
                return task;
            }
        }
        return null;
    }

    public static ArrayList<RepairTask> findByDevice(
        ArrayList<RepairTask> tasks,
        String device
    ) {
        ArrayList<RepairTask> results = new ArrayList<>();

        if (tasks == null || device == null) {
            return results;
        }

        String target = device.trim().toLowerCase();
        if (target.isEmpty()) {
            return results;
        }

        for (RepairTask task : tasks) {
            if (task.getDevice().toLowerCase().contains(target)) {
                results.add(task);
            }
        }
        return results;
    }

    // ---------- 工具 ----------

    public static RepairTask[] toArray(ArrayList<RepairTask> tasks) {
        RepairTask[] result = new RepairTask[tasks.size()];

        for (int index = 0; index < tasks.size(); index++) {
            result[index] = tasks.get(index);
        }
        return result;
    }
}
