// 課堂實作題四要求獨立的 Product.java；
// 概念 9 的 ProductPriceSort 也共用這個類別，
// 避免同一資料夾出現兩個 Product 類別而無法編譯。
public class Product {
    private String id;
    private String name;
    private int price;
    private int stock;

    public Product(String id, String name, int price) {
        this(id, name, price, 0);
    }

    public Product(String id, String name, int price, int stock) {
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
        return id + " " + name + " $" + price;
    }
}
