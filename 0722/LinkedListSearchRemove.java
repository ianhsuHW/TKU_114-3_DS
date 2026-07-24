public class LinkedListSearchRemove {
    public static void main(String[] args) {
        IntNode head = new IntNode(10);
        head.next = new IntNode(20);
        head.next.next = new IntNode(30);
        head.next.next.next = new IntNode(40);

        System.out.print("原始串列：");
        printList(head);

        System.out.println("contains 20: " + contains(head, 20));
        System.out.println("contains 99: " + contains(head, 99));

        head = removeValue(head, 10);
        System.out.print("刪除 head 後：");
        printList(head);

        head = removeValue(head, 30);
        System.out.print("刪除中間 30 後：");
        printList(head);

        head = removeValue(head, 40);
        System.out.print("刪除最後 40 後：");
        printList(head);

        head = removeValue(head, 99);
        System.out.print("嘗試刪除不存在 99：");
        printList(head);
    }

    private static boolean contains(IntNode head, int value) {
        for (IntNode current = head; current != null; current = current.next) {
            if (current.data == value) {
                return true;
            }
        }
        return false;
    }

    private static IntNode removeValue(IntNode head, int value) {
        if (head == null) {
            return null;
        }

        if (head.data == value) {
            return head.next;
        }

        IntNode previous = head;
        for (IntNode current = head.next; current != null; current = current.next) {
            if (current.data == value) {
                previous.next = current.next;
                return head;
            }
            previous = current;
        }

        return head;
    }

    private static void printList(IntNode head) {
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
}
