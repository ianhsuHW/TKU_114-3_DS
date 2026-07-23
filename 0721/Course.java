package w0721;

public class Course {
    private final String code;
    private final String name;
    private final int capacity;
    private int enrolled;

    public Course(String code, String name) { this(code, name, Integer.MAX_VALUE); }
    public Course(String code, String name, int capacity) {
        this.code = code;
        this.name = name;
        this.capacity = Math.max(capacity, 0);
    }
    public String getCode() { return code; }
    public String getName() { return name; }
    public int getCapacity() { return capacity; }
    public int getEnrolled() { return enrolled; }
    public boolean enroll() { if (enrolled >= capacity) return false; enrolled++; return true; }
    public boolean withdraw() { if (enrolled == 0) return false; enrolled--; return true; }
    public boolean isFull() { return enrolled >= capacity; }
    @Override public String toString() { return code + " | " + name + " | enrolled=" + enrolled + "/" + capacity; }
}
