import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class OrderManagementPractice {
    // allOrders 是完整主資料；waiting 與 completed 只代表處理狀態
    private ArrayList<Order> allOrders = new ArrayList<>();
    private Deque<Order> waiting = new ArrayDeque<>();
    private Deque<Order> completed = new ArrayDeque<>();

    public static void main(String[] args) {
        OrderManagementPractice system = new OrderManagementPractice();

        System.out.println("=== 空 Queue 與空 Stack 測試 ===");
        System.out.println("下一筆待處理：" + system.peekNext());
        System.out.println("處理下一筆：" + system.processNext());
        System.out.println("復原：" + system.undoLast());

        System.out.println();
        System.out.println("=== 新增訂單（含重複編號測試）===");
        System.out.println("O205 " + system.addOrder(
            new Order("O205", "Amy", 1800)));
        System.out.println("O101 " + system.addOrder(
            new Order("O101", "Ben", 650)));
        System.out.println("O330 " + system.addOrder(
            new Order("O330", "Amy", 2400)));
        System.out.println("O150 " + system.addOrder(
            new Order("O150", "Cara", 990)));
        System.out.println("O220 " + system.addOrder(
            new Order("O220", "Ben", 2400)));
        System.out.println("o101（重複編號）" + system.addOrder(
            new Order("o101", "重複", 100)));
        System.out.println("null " + system.addOrder(null));

        System.out.println();
        System.out.println("=== 依編號升冪 ===");
        Order[] byId = system.createSortedById();
        printOrders(byId);

        System.out.println();
        System.out.println("=== 依金額降冪（新增功能，相同金額保持原順序）===");
        Order[] byAmount = system.createSortedByAmountDescending();
        printOrders(byAmount);

        System.out.println();
        System.out.println("=== 依顧客姓名搜尋全部訂單 ===");
        System.out.println("Amy：" + system.findByCustomer("Amy"));
        System.out.println("ben：" + system.findByCustomer("ben"));
        System.out.println("Zoe（找不到）：" + system.findByCustomer("Zoe"));

        System.out.println();
        System.out.println("=== 處理流程 ===");
        System.out.println("下一筆待處理：" + system.peekNext());
        System.out.println("處理：" + system.processNext());
        System.out.println("處理：" + system.processNext());
        System.out.println("下一筆待處理：" + system.peekNext());
        System.out.println("復原：" + system.undoLast());
        System.out.println("下一筆待處理：" + system.peekNext());

        System.out.println();
        System.out.println("等待處理：" + system.waiting);
        System.out.println("完成紀錄：" + system.completed);
        System.out.println("主資料筆數：" + system.allOrders.size());
    }

    // 防止重複訂單編號
    public boolean addOrder(Order order) {
        if (order == null) {
            return false;
        }

        String id = order.getId();
        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        if (OrderAlgorithms.containsId(allOrders, id)) {
            return false;
        }

        allOrders.add(order);
        waiting.offer(order);
        return true;
    }

    // 顯示下一筆待處理訂單，不移除
    public Order peekNext() {
        if (waiting.isEmpty()) {
            return null;
        }
        return waiting.peek();
    }

    public Order processNext() {
        if (waiting.isEmpty()) {
            return null;
        }
        Order order = waiting.poll();
        completed.push(order);
        return order;
    }

    public Order undoLast() {
        if (completed.isEmpty()) {
            return null;
        }
        Order order = completed.pop();
        waiting.offerFirst(order);
        return order;
    }

    public Order[] createSortedById() {
        Order[] copy = allOrders.toArray(new Order[0]);
        OrderAlgorithms.mergeSortById(copy);
        return copy;
    }

    public Order[] createSortedByAmountDescending() {
        Order[] copy = allOrders.toArray(new Order[0]);
        OrderAlgorithms.mergeSortByAmountDescending(copy);
        return copy;
    }

    public ArrayList<Order> findByCustomer(String customer) {
        return OrderAlgorithms.findByCustomer(allOrders, customer);
    }

    public static void printOrders(Order[] orders) {
        if (orders.length == 0) {
            System.out.println("沒有訂單");
            return;
        }
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
