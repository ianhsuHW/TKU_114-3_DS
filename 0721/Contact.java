package w0721;

public class Contact {
    private final String code;
    private final String name;
    private String phone;
    private final String email;
    public Contact(String code, String name, String phone, String email) { this.code=code; this.name=name; this.phone=phone; this.email=email; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public void setPhone(String phone) { this.phone = phone; }
    @Override public String toString() { return code + " | " + name + " | " + phone + " | " + email; }
}
