public class PlaylistNode {
    String code;
    String name;
    PlaylistNode next;

    public PlaylistNode(String code, String name) {
        this.code = code;
        this.name = name;
        this.next = null;
    }

    @Override
    public String toString() {
        return code + ": " + name;
    }
}
