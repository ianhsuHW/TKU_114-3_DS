public class Registration {
    private String id;
    private String name;
    private String phone;

    public Registration(String id, String name, String phone) {
        if (id == null) {
            this.id = "";
        } else {
            this.id = id.trim();
        }

        if (name == null) {
            this.name = "";
        } else {
            this.name = name.trim();
        }

        if (phone == null) {
            this.phone = "";
        } else {
            this.phone = phone.trim();
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return id + " " + name + " 電話=" + phone;
    }
}
