import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CounterServiceSystem {
    private Deque<String> waiting = new ArrayDeque<>();
    private List<String> served = new ArrayList<>();
    private int nextNumber = 1;

    public String takeNumber(String name) {
        String ticket = "A" + String.format("%03d", nextNumber) + " " + name;
        nextNumber++;
        waiting.offer(ticket);
        System.out.println("取號：" + ticket);
        return ticket;
    }

    public void callNext() {
        String ticket = waiting.poll();
        if (ticket == null) {
            System.out.println("目前無人等待");
            return;
        }
        served.add(ticket);
        System.out.println("叫號：" + ticket);
    }

    public void peekNext() {
        String ticket = waiting.peek();
        if (ticket == null) {
            System.out.println("下一位：目前無人等待");
        } else {
            System.out.println("下一位：" + ticket);
        }
    }

    public void waitingCount() {
        System.out.println("等待人數：" + waiting.size());
    }

    public void printServed() {
        System.out.println("已服務：" + served);
    }

    public static void main(String[] args) {
        CounterServiceSystem counter = new CounterServiceSystem();

        counter.takeNumber("Amy");
        counter.takeNumber("Ben");
        counter.takeNumber("Cara");
        counter.peekNext();
        counter.waitingCount();

        counter.callNext();
        counter.callNext();
        counter.peekNext();
        counter.waitingCount();

        counter.callNext();
        counter.callNext();

        counter.printServed();
    }
}
