package w0721;

import java.util.ArrayList;

public class ShoppingCartSystem {
    public static void main(String[] args) {
        ArrayList<CartItem> cart = new ArrayList<>();
        add(cart, "P01", "Keyboard", 890, 1);
        add(cart, "P01", "Keyboard", 890, 2);
        for (CartItem item : cart) System.out.println(item);
        System.out.println("總額：" + total(cart));
    }
    public static CartItem find(ArrayList<CartItem> cart, String code) { for (CartItem item : cart) if (item.getCode().equalsIgnoreCase(code)) return item; return null; }
    public static boolean add(ArrayList<CartItem> cart, String code, String name, double price, int quantity) { if (quantity <= 0 || price < 0) return false; CartItem item=find(cart,code); if(item!=null) item.addQuantity(quantity); else cart.add(new CartItem(code,name,price,quantity)); return true; }
    public static boolean updateQuantity(ArrayList<CartItem> cart, String code, int quantity) { CartItem item=find(cart,code); return item != null && item.setQuantity(quantity); }
    public static boolean remove(ArrayList<CartItem> cart, String code) { CartItem item=find(cart,code); return item != null && cart.remove(item); }
    public static double total(ArrayList<CartItem> cart) { double total=0; for(CartItem item:cart) total+=item.subtotal(); return total; }
}
