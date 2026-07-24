public class TaskLinkedList {
    private TaskNode head;
    private int size;

    public boolean addFirst(String code, String description) {
        if (!valid(code, description)) {
            return false;
        }
        TaskNode node = new TaskNode(code.trim(), description.trim());
        node.next = head;
        head = node;
        size++;
        return true;
    }

    public boolean addLast(String code, String description) {
        if (!valid(code, description)) {
            return false;
        }
        TaskNode node = new TaskNode(code.trim(), description.trim());
        if (head == null) {
            head = node;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
        size++;
        return true;
    }

    private boolean valid(String code, String description) {
        return code != null && !code.isBlank() && description != null && !description.isBlank() && find(code) == null;
    }

    public TaskNode find(String code) {
        for (TaskNode current = head; current != null; current = current.next) {
            if (current.code.equalsIgnoreCase(code)) {
                return current;
            }
        }
        return null;
    }

    public boolean complete(String code) {
        TaskNode task = find(code);
        if (task == null) {
            return false;
        }
        task.completed = true;
        return true;
    }

    public boolean remove(String code) {
        if (head == null) {
            return false;
        }
        if (head.code.equalsIgnoreCase(code)) {
            head = head.next;
            size--;
            return true;
        }
        TaskNode previous = head;
        for (TaskNode current = head.next; current != null; current = current.next) {
            if (current.code.equalsIgnoreCase(code)) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public int unfinishedCount() {
        int count = 0;
        for (TaskNode current = head; current != null; current = current.next) {
            if (!current.completed) {
                count++;
            }
        }
        return count;
    }

    public void printUnfinished() {
        if (head == null) {
            System.out.println("目前沒有工作項目");
            return;
        }
        for (TaskNode current = head; current != null; current = current.next) {
            if (!current.completed) {
                System.out.println(current);
            }
        }
    }

    public void printAll() {
        if (head == null) {
            System.out.println("目前沒有工作項目");
            return;
        }
        for (TaskNode current = head; current != null; current = current.next) {
            System.out.println(current);
        }
    }
}
