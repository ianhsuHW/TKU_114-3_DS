public class Q11_LabDevice {
    private String code;
    private String name;
    private int usageHours;
    private boolean active;

    public Q11_LabDevice(String code, String name, int usageHours, boolean active) {
        this.code = normalize(code, "UNKNOWN");
        this.name = normalize(name, "Unnamed");
        this.usageHours = Math.max(0, usageHours);
        this.active = active;
    }
    private static String normalize(String value, String fallback) {
        if (value == null || value.trim().isEmpty()) return fallback;
        return value.trim();
    }
    public String getCode() { return code; }
    public String getName() { return name; }
    public int getUsageHours() { return usageHours; }
    public boolean isActive() { return active; }
    public void setName(String name) { this.name = normalize(name, "Unnamed"); }
    public void addUsageHours(int hours) { if (hours > 0) usageHours += hours; }
    public void deactivate() { active = false; }
    public boolean needsMaintenance() { return usageHours >= 100; }
    @Override public String toString() { return code + " | " + name + " | " + usageHours + " hours | " + (active ? "active" : "inactive"); }

    public static void main(String[] args) {
        Q11_LabDevice device = new Q11_LabDevice(" D01 ", " Printer ", 90, true);
        device.addUsageHours(30); device.addUsageHours(-5); device.setName(" 3D Printer ");
        System.out.println(device);
        System.out.println("Needs maintenance: " + device.needsMaintenance());
        device.deactivate();
        System.out.println("Active: " + device.isActive());
    }
}
