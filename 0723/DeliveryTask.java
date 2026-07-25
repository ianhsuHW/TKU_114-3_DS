public class DeliveryTask {
    private String id;
    private String destination;

    public DeliveryTask(String id, String destination) {
        this.id = id;
        this.destination = destination;
    }

    public String getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return id + "（" + destination + "）";
    }
}
