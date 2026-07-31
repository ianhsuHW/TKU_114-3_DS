import java.util.ArrayList;

public class OrderAlgorithms {

    // ================= 概念 10：依編號升冪 Merge Sort =================

    public static void mergeSortById(Order[] orders) {
        Order[] temp = new Order[orders.length];
        mergeSortById(orders, temp, 0, orders.length - 1);
    }

    private static void mergeSortById(
        Order[] orders,
        Order[] temp,
        int left,
        int right
    ) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSortById(orders, temp, left, mid);
        mergeSortById(orders, temp, mid + 1, right);
        merge(orders, temp, left, mid, right);
    }

    private static void merge(
        Order[] orders,
        Order[] temp,
        int left,
        int mid,
        int right
    ) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (orders[i].getId().compareTo(
                    orders[j].getId()) <= 0) {
                temp[k++] = orders[i++];
            } else {
                temp[k++] = orders[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = orders[i++];
        }
        while (j <= right) {
            temp[k++] = orders[j++];
        }
        for (int index = left; index <= right; index++) {
            orders[index] = temp[index];
        }
    }

    public static int binarySearchById(
        Order[] orders,
        String targetId
    ) {
        int low = 0;
        int high = orders.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = targetId.compareTo(
                orders[mid].getId()
            );

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static ArrayList<Order> findByCustomer(
        ArrayList<Order> orders,
        String customer
    ) {
        ArrayList<Order> results = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomer().equalsIgnoreCase(customer)) {
                results.add(order);
            }
        }
        return results;
    }

    // ============ 課堂實作題四新增：依金額降冪 Merge Sort ============

    public static void mergeSortByAmountDescending(Order[] orders) {
        if (orders == null || orders.length < 2) {
            return;
        }

        Order[] temp = new Order[orders.length];
        mergeSortByAmount(orders, temp, 0, orders.length - 1);
    }

    private static void mergeSortByAmount(
        Order[] orders,
        Order[] temp,
        int left,
        int right
    ) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSortByAmount(orders, temp, left, mid);
        mergeSortByAmount(orders, temp, mid + 1, right);
        mergeByAmount(orders, temp, left, mid, right);
    }

    private static void mergeByAmount(
        Order[] orders,
        Order[] temp,
        int left,
        int mid,
        int right
    ) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            // 金額相同時先取左側，保持原本順序（穩定）
            if (orders[i].getAmount() >= orders[j].getAmount()) {
                temp[k++] = orders[i++];
            } else {
                temp[k++] = orders[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = orders[i++];
        }
        while (j <= right) {
            temp[k++] = orders[j++];
        }
        for (int index = left; index <= right; index++) {
            orders[index] = temp[index];
        }
    }

    // ============ 課堂實作題四新增：防止重複訂單編號 ============

    public static boolean containsId(
        ArrayList<Order> orders,
        String id
    ) {
        if (orders == null || id == null) {
            return false;
        }

        String target = id.trim();
        for (Order order : orders) {
            if (order.getId().equalsIgnoreCase(target)) {
                return true;
            }
        }
        return false;
    }
}
