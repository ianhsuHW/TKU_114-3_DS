package w0721;

import java.util.ArrayList;

public class ContactBookSystem {
    public static void main(String[] args) {
        ArrayList<Contact> contacts = new ArrayList<>();
        add(contacts, new Contact("A01", "Amy", "0912345678", "amy@example.com"));
        Contact contact = find(contacts, "A01");
        if (contact != null) updatePhone(contact, "0987654321");
        list(contacts);
    }
    public static boolean add(ArrayList<Contact> contacts, Contact contact) { if (contact.getName().trim().isEmpty() || find(contacts, contact.getCode()) != null) return false; contacts.add(contact); return true; }
    public static Contact find(ArrayList<Contact> contacts, String code) { for (Contact c : contacts) if (c.getCode().equalsIgnoreCase(code)) return c; return null; }
    public static boolean updatePhone(Contact contact, String phone) { if (phone == null || phone.trim().isEmpty()) return false; contact.setPhone(phone.trim()); return true; }
    public static boolean remove(ArrayList<Contact> contacts, String code) { Contact c=find(contacts, code); return c != null && contacts.remove(c); }
    public static void list(ArrayList<Contact> contacts) { for (Contact c : contacts) System.out.println(c); }
}
