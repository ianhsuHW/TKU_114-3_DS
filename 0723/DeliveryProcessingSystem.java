import java.util.ArrayDeque;
import java.util.Deque;

public class DeliveryProcessingSystem {
    private Deque<DeliveryTask> waiting = new ArrayDeque<>();
    private Deque<DeliveryTask> completed = new ArrayDeque<>();

    public void addTask(DeliveryTask task) {
        waiting.offer(task);
        System.out.println("新增工作：" + task);
    }

    public void completeNext() {
        DeliveryTask task = waiting.poll();
        if (task == null) {
            System.out.println("目前沒有待配送工作");
            return;
        }
        completed.push(task);
        System.out.println("完成配送：" + task);
    }

    public void peekNext() {
        DeliveryTask task = waiting.peek();
        if (task == null) {
            System.out.println("下一筆：目前沒有待配送工作");
        } else {
            System.out.println("下一筆：" + task);
        }
    }

    public void undoLastCompleted() {
        DeliveryTask task = completed.poll();
        if (task == null) {
            System.out.println("沒有可復原的完成紀錄");
            return;
        }
        waiting.offer(task);
        System.out.println("復原完成，回到等待佇列：" + task);
    }

    public void printCounts() {
        System.out.println("等待數：" + waiting.size() + "，完成數：" + completed.size());
    }

    public void printAllRecords() {
        System.out.println("等待中：" + waiting);
        System.out.println("完成紀錄：" + completed);
    }

    public static void main(String[] args) {
        DeliveryProcessingSystem system = new DeliveryProcessingSystem();

        system.addTask(new DeliveryTask("D001", "台北"));
        system.addTask(new DeliveryTask("D002", "台中"));
        system.addTask(new DeliveryTask("D003", "高雄"));

        system.peekNext();
        system.completeNext();
        system.completeNext();
        system.printCounts();

        system.undoLastCompleted();
        system.printCounts();

        system.printAllRecords();

        system.completeNext();
        system.completeNext();
        system.completeNext();

        system.printAllRecords();
    }
}
