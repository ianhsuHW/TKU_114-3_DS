public class TaskNode {
    String code;
    String description;
    boolean completed;
    TaskNode next;

    public TaskNode(String code, String description) {
        this.code = code;
        this.description = description;
        this.completed = false;
        this.next = null;
    }

    @Override
    public String toString() {
        return code + ": " + description + (completed ? " (completed)" : "");
    }
}
