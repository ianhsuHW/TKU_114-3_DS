public class PlaylistLinkedList {
    private PlaylistNode head;

    public boolean addLast(String code, String name) {
        if (code == null || code.isBlank() || name == null || name.isBlank()) {
            return false;
        }
        if (find(code) != null) {
            return false;
        }
        PlaylistNode node = new PlaylistNode(code, name);
        if (head == null) {
            head = node;
        } else {
            PlaylistNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
        return true;
    }

    public PlaylistNode find(String code) {
        for (PlaylistNode current = head; current != null; current = current.next) {
            if (current.code.equalsIgnoreCase(code)) {
                return current;
            }
        }
        return null;
    }

    public boolean remove(String code) {
        if (head == null) {
            return false;
        }
        if (head.code.equalsIgnoreCase(code)) {
            head = head.next;
            return true;
        }
        PlaylistNode previous = head;
        for (PlaylistNode current = head.next; current != null; current = current.next) {
            if (current.code.equalsIgnoreCase(code)) {
                previous.next = current.next;
                return true;
            }
            previous = current;
        }
        return false;
    }

    public void print() {
        if (head == null) {
            System.out.println("播放清單為空");
            return;
        }
        for (PlaylistNode current = head; current != null; current = current.next) {
            System.out.println(current);
        }
    }
}
