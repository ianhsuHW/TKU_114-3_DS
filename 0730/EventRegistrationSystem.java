import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class EventRegistrationSystem {
    private static final int CAPACITY = 3;

    private ArrayList<Registration> allRegistrations = new ArrayList<>();
    private ArrayList<Registration> confirmed = new ArrayList<>();
    private Deque<Registration> waitlist = new ArrayDeque<>();
    private Deque<Registration> cancelled = new ArrayDeque<>();

    public static void main(String[] args) {
        EventRegistrationSystem system = new EventRegistrationSystem();

        System.out.println("=== 空資料測試（名額 " + CAPACITY + " 人）===");
        System.out.println("取消不存在的編號：" + system.cancel("R999"));
        System.out.println("復原（無取消紀錄）：" + system.undoCancel());
        System.out.println("候補出列（空 Queue）：" + system.promoteNext());
        system.printStatus();

        System.out.println();
        System.out.println("=== 報名 ===");
        System.out.println("R205 " + system.register("R205", "Amy Chen", "0911-111111"));
        System.out.println("R101 " + system.register("R101", "Ben Lin", "0922-222222"));
        System.out.println("R330 " + system.register("R330", "Cara Wu", "0933-333333"));
        System.out.println("R150（額滿轉候補）" +
            system.register("R150", "Dan Hsu", "0944-444444"));
        System.out.println("R410（額滿轉候補）" +
            system.register("R410", "Ella Kuo", "0955-555555"));
        System.out.println("r101（重複編號）" +
            system.register("r101", "重複報名", "0900-000000"));
        System.out.println("空編號 " + system.register("  ", "無編號", "0900-000000"));
        System.out.println("null 編號 " + system.register(null, "無編號", "0900-000000"));

        system.printStatus();

        System.out.println();
        System.out.println("=== 依編號升冪（Merge Sort）===");
        Registration[] sorted = system.createSortedById();
        for (Registration registration : sorted) {
            System.out.println(registration);
        }

        System.out.println();
        System.out.println("=== Binary Search 依編號查詢 ===");
        System.out.println("查詢 R330：" + system.searchById("R330"));
        System.out.println("查詢 r150：" + system.searchById("r150"));
        System.out.println("查詢 R999：" + system.searchById("R999"));

        System.out.println();
        System.out.println("=== Sequential Search 依姓名查詢 ===");
        System.out.println("查詢 chen：" + system.searchByName("chen"));
        System.out.println("查詢 Zoe：" + system.searchByName("Zoe"));

        System.out.println();
        System.out.println("=== 取消與候補遞補 ===");
        System.out.println("取消 R101：" + system.cancel("R101"));
        system.printStatus();

        System.out.println();
        System.out.println("=== 復原最近一次取消 ===");
        System.out.println("復原：" + system.undoCancel());
        system.printStatus();
    }

    public boolean register(String id, String name, String phone) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        if (findById(id) != null) {
            return false;
        }

        Registration registration = new Registration(id, name, phone);
        allRegistrations.add(registration);

        if (confirmed.size() < CAPACITY) {
            confirmed.add(registration);
        } else {
            waitlist.offer(registration);
        }
        return true;
    }

    public Registration cancel(String id) {
        Registration target = findById(id);
        if (target == null) {
            return null;
        }

        boolean removed = removeFromConfirmed(target);
        if (!removed) {
            removed = removeFromWaitlist(target);
        }

        if (!removed) {
            return null;
        }

        removeFromAll(target);
        cancelled.push(target);

        // 名額空出時由候補 Queue 依序遞補
        promoteNext();
        return target;
    }

    public Registration promoteNext() {
        if (waitlist.isEmpty()) {
            return null;
        }
        if (confirmed.size() >= CAPACITY) {
            return null;
        }

        Registration next = waitlist.poll();
        confirmed.add(next);
        return next;
    }

    public Registration undoCancel() {
        if (cancelled.isEmpty()) {
            return null;
        }

        Registration restored = cancelled.pop();
        allRegistrations.add(restored);

        if (confirmed.size() < CAPACITY) {
            confirmed.add(restored);
        } else {
            waitlist.offerFirst(restored);
        }
        return restored;
    }

    public Registration[] createSortedById() {
        Registration[] copy =
            RegistrationAlgorithms.toArray(allRegistrations);
        RegistrationAlgorithms.mergeSortById(copy);
        return copy;
    }

    public Registration searchById(String id) {
        Registration[] sorted = createSortedById();
        return RegistrationAlgorithms.binarySearchById(sorted, id);
    }

    public ArrayList<Registration> searchByName(String name) {
        return RegistrationAlgorithms.findByName(allRegistrations, name);
    }

    public Registration findById(String id) {
        if (id == null) {
            return null;
        }

        String target = id.trim();
        if (target.isEmpty()) {
            return null;
        }

        for (Registration registration : allRegistrations) {
            if (registration.getId().equalsIgnoreCase(target)) {
                return registration;
            }
        }
        return null;
    }

    private boolean removeFromConfirmed(Registration target) {
        for (int index = 0; index < confirmed.size(); index++) {
            if (confirmed.get(index) == target) {
                confirmed.remove(index);
                return true;
            }
        }
        return false;
    }

    // 只使用 poll 與 offer 重建 Queue，順序保持不變
    private boolean removeFromWaitlist(Registration target) {
        int size = waitlist.size();
        boolean removed = false;

        for (int count = 0; count < size; count++) {
            Registration current = waitlist.poll();

            if (!removed && current == target) {
                removed = true;
            } else {
                waitlist.offer(current);
            }
        }
        return removed;
    }

    private void removeFromAll(Registration target) {
        for (int index = 0; index < allRegistrations.size(); index++) {
            if (allRegistrations.get(index) == target) {
                allRegistrations.remove(index);
                return;
            }
        }
    }

    public void printStatus() {
        System.out.println("狀態：正取 " + confirmed.size()
            + "/" + CAPACITY
            + "｜候補 " + waitlist.size()
            + "｜取消紀錄 " + cancelled.size()
            + "｜全部 " + allRegistrations.size());
        System.out.println("  正取：" + confirmed);
        System.out.println("  候補：" + waitlist);
        System.out.println("  取消：" + cancelled);
    }
}
