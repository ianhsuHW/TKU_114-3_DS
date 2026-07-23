package w0721;

import java.util.ArrayList;

public class EquipmentManager {
    public static void main(String[] args) {
        ArrayList<Equipment> items = new ArrayList<>();
        add(items, new Equipment("E01", "Laptop"));
        add(items, new Equipment("E02", "Projector"));
        Equipment item = find(items, "E01");
        if (item != null) item.borrow();
        for (Equipment e : items) if (e.isAvailable()) System.out.println(e);
    }
    public static boolean add(ArrayList<Equipment> items, Equipment item) { if (find(items, item.getCode()) != null) return false; items.add(item); return true; }
    public static Equipment find(ArrayList<Equipment> items, String code) { for (Equipment item : items) if (item.getCode().equalsIgnoreCase(code)) return item; return null; }
}
