package w0721;

public class Equipment {
    private final String code;
    private final String name;
    private boolean available = true;
    public Equipment(String code, String name) { this.code=code; this.name=name; }
    public String getCode() { return code; }
    public boolean isAvailable() { return available; }
    public boolean borrow() { if (!available) return false; available=false; return true; }
    public boolean returnItem() { if (available) return false; available=true; return true; }
    @Override public String toString() { return code + " | " + name + " | " + (available ? "可借" : "借出中"); }
}
