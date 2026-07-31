import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class RepairSchedulingSystem {
    private ArrayList<RepairTask> allTasks = new ArrayList<>();
    private Deque<RepairTask> waiting = new ArrayDeque<>();
    private Deque<RepairTask> completed = new ArrayDeque<>();
    private int registerCounter = 0;

    public static void main(String[] args) {
        RepairSchedulingSystem system = new RepairSchedulingSystem();

        System.out.println("=== 空結構測試 ===");
        System.out.println("處理下一筆：" + system.processNext());
        System.out.println("復原：" + system.undoLast());
        system.printStatistics();

        System.out.println();
        System.out.println("=== 登記維修工作 ===");
        System.out.println("R201 " + system.register("R201", "印表機", 2));
        System.out.println("R202 " + system.register("R202", "投影機", 5));
        System.out.println("R203 " + system.register("R203", "冷氣主機", 2));
        System.out.println("R204 " + system.register("R204", "網路交換器", 5));
        System.out.println("R205 " + system.register("R205", "印表機", 1));
        System.out.println("r201（重複）" + system.register("r201", "印表機", 3));
        System.out.println("空編號 " + system.register("  ", "螢幕", 1));

        System.out.println();
        System.out.println("=== 全部工作（ArrayList，登記順序）===");
        system.printAll();

        System.out.println();
        System.out.println("=== 依優先等級降冪（Merge Sort，同等級保持登記順序）===");
        RepairTask[] sorted = system.createSortedByPriority();
        for (RepairTask task : sorted) {
            System.out.println(task);
        }

        System.out.println();
        System.out.println("=== Queue 處理流程 ===");
        System.out.println("下一筆：" + system.peekNext());
        System.out.println("完成：" + system.processNext());
        System.out.println("完成：" + system.processNext());
        System.out.println("下一筆：" + system.peekNext());

        System.out.println();
        System.out.println("=== Stack 復原 ===");
        System.out.println("復原：" + system.undoLast());
        System.out.println("下一筆：" + system.peekNext());

        System.out.println();
        System.out.println("=== 搜尋 ===");
        System.out.println("依編號 r203：" + system.searchById("r203"));
        System.out.println("依編號 R999：" + system.searchById("R999"));
        System.out.println("依設備 印表機：" + system.searchByDevice("印表機"));
        System.out.println("依設備 螢幕：" + system.searchByDevice("螢幕"));

        System.out.println();
        system.printStatistics();
    }

    public boolean register(String id, String device, int priority) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        if (RepairAlgorithms.findById(allTasks, id) != null) {
            return false;
        }

        registerCounter++;
        RepairTask task =
            new RepairTask(id, device, priority, registerCounter);

        allTasks.add(task);
        waiting.offer(task);
        return true;
    }

    public RepairTask processNext() {
        if (waiting.isEmpty()) {
            return null;
        }

        RepairTask task = waiting.poll();
        completed.push(task);
        return task;
    }

    public RepairTask undoLast() {
        if (completed.isEmpty()) {
            return null;
        }

        RepairTask task = completed.pop();
        waiting.offerFirst(task);
        return task;
    }

    public RepairTask peekNext() {
        if (waiting.isEmpty()) {
            return null;
        }
        return waiting.peek();
    }

    public RepairTask[] createSortedByPriority() {
        RepairTask[] copy = RepairAlgorithms.toArray(allTasks);
        RepairAlgorithms.mergeSortByPriority(copy);
        return copy;
    }

    public RepairTask searchById(String id) {
        return RepairAlgorithms.findById(allTasks, id);
    }

    public ArrayList<RepairTask> searchByDevice(String device) {
        return RepairAlgorithms.findByDevice(allTasks, device);
    }

    public void printAll() {
        if (allTasks.isEmpty()) {
            System.out.println("目前沒有維修工作");
            return;
        }

        for (RepairTask task : allTasks) {
            System.out.println(task);
        }
    }

    public void printStatistics() {
        System.out.println("統計：等待 " + waiting.size()
            + " 筆｜完成 " + completed.size()
            + " 筆｜全部 " + allTasks.size() + " 筆");
        System.out.println("  等待中：" + waiting);
        System.out.println("  已完成：" + completed);
    }
}
