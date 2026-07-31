public class StoreProduct {
    private String id;
    private String name;
    private int price;
    private int stock;

    public StoreProduct(
        String id,
        String name,
        int price,
        int stock
    ) {
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

        if (price < 0) {
            this.price = 0;
        } else {
            this.price = price;
        }

        if (stock < 0) {
            this.stock = 0;
        } else {
            this.stock = stock;
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return id + " " + name
            + " 價格=" + price
            + " 庫存=" + stock;
    }
}
