package w0721;

public class CartItem {
    private final String code;
    private final String name;
    private final double price;
    private int quantity;
    public CartItem(String code, String name, double price, int quantity) { this.code=code; this.name=name; this.price=Math.max(price, 0); this.quantity=Math.max(quantity, 1); }
    public String getCode() { return code; }
    public void addQuantity(int amount) { if (amount > 0) quantity += amount; }
    public boolean setQuantity(int quantity) { if (quantity <= 0) return false; this.quantity=quantity; return true; }
    public double subtotal() { return price * quantity; }
    @Override public String toString() { return code + " | " + name + " | " + price + " x " + quantity + " = " + subtotal(); }
}
