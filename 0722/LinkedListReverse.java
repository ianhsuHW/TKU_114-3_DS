public class LinkedListReverse {
    public static void main(String[] args) {
        IntNode head = null;
        head = reverse(head);
        printList(head);

        head = new IntNode(10);
        head = reverse(head);
        printList(head);

        head = new IntNode(10);
        head.next = new IntNode(20);
        head.next.next = new IntNode(30);
        head.next.next.next = new IntNode(40);
        System.out.print("原始串列：");
        printList(head);

        head = reverse(head);
        System.out.print("反轉後：");
        printList(head);
    }

    private static IntNode reverse(IntNode head) {
        IntNode previous = null;
        IntNode current = head;

        while (current != null) {
            IntNode next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        return previous;
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
