public class ProductSortPractice {
    public static void main(String[] args) {
        // 至少 8 筆，且包含相同價格（1290 與 890 各兩筆）
        Product[] products = {
            new Product("P101", "Keyboard", 1290, 8),
            new Product("P102", "Mouse", 650, 15),
            new Product("P103", "Monitor", 5200, 5),
            new Product("P104", "Webcam", 1290, 3),
            new Product("P105", "USB Hub", 450, 22),
            new Product("P106", "Laptop Stand", 890, 11),
            new Product("P107", "Headset", 1850, 6),
            new Product("P108", "Cable", 890, 40),
            new Product("P109", "Docking Station", 3400, 2)
        };

        System.out.println("=== 排序前 ===");
        printProducts(products);

        insertionSortByPrice(products);

        System.out.println();
        System.out.println("=== 依價格升冪排序後（相同價格保持原本順序）===");
        printProducts(products);

        System.out.println();
        System.out.println("穩定性檢查：");
        System.out.println("  價格 890：P106 應在 P108 之前");
        System.out.println("  價格 1290：P101 應在 P104 之前");
    }

    public static void insertionSortByPrice(Product[] products) {
        for (int index = 1; index < products.length; index++) {
            // 移動整個物件，欄位不會分開
            Product key = products[index];
            int position = index - 1;

            // 使用 > 而不是 >=，相同價格時不會互換，保持穩定
            while (position >= 0 &&
                   products[position].getPrice() > key.getPrice()) {
                products[position + 1] = products[position];
                position--;
            }
            products[position + 1] = key;
        }
    }

    public static void printProducts(Product[] products) {
        System.out.printf(
            "%-6s %-18s %8s %8s%n",
            "編號", "名稱", "價格", "庫存"
        );

        for (Product product : products) {
            System.out.printf(
                "%-6s %-18s %8d %8d%n",
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
            );
        }
    }
}
