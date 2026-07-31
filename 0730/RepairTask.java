public class RepairTask {
    private String id;
    private String device;
    private int priority;
    private int registerOrder;

    public RepairTask(
        String id,
        String device,
        int priority,
        int registerOrder
    ) {
        if (id == null) {
            this.id = "";
        } else {
            this.id = id.trim();
        }

        if (device == null) {
            this.device = "";
        } else {
            this.device = device.trim();
        }

        if (priority < 0) {
            this.priority = 0;
        } else {
            this.priority = priority;
        }

        if (registerOrder < 0) {
            this.registerOrder = 0;
        } else {
            this.registerOrder = registerOrder;
        }
    }

    public String getId() {
        return id;
    }

    public String getDevice() {
        return device;
    }

    public int getPriority() {
        return priority;
    }

    public int getRegisterOrder() {
        return registerOrder;
    }

    @Override
    public String toString() {
        return id + " " + device
            + " 等級=" + priority
            + " 登記=" + registerOrder;
    }
}
