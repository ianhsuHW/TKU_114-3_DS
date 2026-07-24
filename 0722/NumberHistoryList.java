public class NumberHistoryList {
    private IntNode head;
    private int size;

    public void addFirst(int value) {
        IntNode node = new IntNode(value);
        node.next = head;
        head = node;
        size++;
    }

    public void addLast(int value) {
        IntNode node = new IntNode(value);
        if (head == null) {
            head = node;
        } else {
            IntNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
        size++;
    }

    public boolean contains(int value) {
        for (IntNode current = head; current != null; current = current.next) {
            if (current.data == value) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(int value) {
        if (head == null) {
            return false;
        }
        if (head.data == value) {
            head = head.next;
            size--;
            return true;
        }
        IntNode previous = head;
        for (IntNode current = head.next; current != null; current = current.next) {
            if (current.data == value) {
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

    public int sum() {
        int total = 0;
        for (IntNode current = head; current != null; current = current.next) {
            total += current.data;
        }
        return total;
    }

    public int max() {
        if (head == null) {
            return Integer.MIN_VALUE;
        }
        int max = head.data;
        for (IntNode current = head.next; current != null; current = current.next) {
            if (current.data > max) {
                max = current.data;
            }
        }
        return max;
    }

    public int min() {
        if (head == null) {
            return Integer.MAX_VALUE;
        }
        int min = head.data;
        for (IntNode current = head.next; current != null; current = current.next) {
            if (current.data < min) {
                min = current.data;
            }
        }
        return min;
    }

    public void printList() {
        if (head == null) {
            System.out.println("串列為空");
            return;
        }
        for (IntNode current = head; current != null; current = current.next) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        NumberHistoryList history = new NumberHistoryList();
        history.addLast(10);
        history.addFirst(5);
        history.addLast(20);
        history.addFirst(2);
        history.addLast(15);
        history.addLast(25);
        history.addFirst(1);
        history.addLast(30);

        System.out.print("目前串列：");
        history.printList();
        System.out.println("size=" + history.size());
        System.out.println("sum=" + history.sum());
        System.out.println("max=" + history.max());
        System.out.println("min=" + history.min());

        System.out.println("contains 20=" + history.contains(20));
        System.out.println("contains 99=" + history.contains(99));

        history.remove(1);
        System.out.print("刪除 1 後：");
        history.printList();

        history.remove(20);
        System.out.print("刪除 20 後：");
        history.printList();

        history.remove(30);
        System.out.print("刪除 30 後：");
        history.printList();
    }
}
