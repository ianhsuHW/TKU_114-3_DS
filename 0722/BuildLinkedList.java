public class BuildLinkedList {
    public static void main(String[] args) {
        IntNode head = new IntNode(10);
        head.next = new IntNode(20);
        head.next.next = new IntNode(30);
        head.next.next.next = new IntNode(40);

        printList(head);
        System.out.println("節點數 = " + countNodes(head));
        System.out.println("總和 = " + sumNodes(head));
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

    private static int countNodes(IntNode head) {
        int count = 0;
        for (IntNode current = head; current != null; current = current.next) {
            count++;
        }
        return count;
    }

    private static int sumNodes(IntNode head) {
        int sum = 0;
        for (IntNode current = head; current != null; current = current.next) {
            sum += current.data;
        }
        return sum;
    }
}
