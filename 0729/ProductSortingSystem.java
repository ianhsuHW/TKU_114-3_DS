public class ProductSortingSystem {
    public static void main(String[] args) {
        StoreProduct[] original = buildProducts();

        System.out.println("=== 原始資料（共 " + original.length + " 筆）===");
        printProducts(original);

        runMode(original, "價格", "升冪");
        runMode(original, "價格", "降冪");
        runMode(original, "庫存", "降冪");

        System.out.println();
        System.out.println("=== 確認原始資料未被改變 ===");
        printProducts(original);
    }

    public static StoreProduct[] buildProducts() {
        return new StoreProduct[] {
            new StoreProduct("P101", "Keyboard", 1200, 8),
            new StoreProduct("P102", "Wireless Mouse", 650, 15),
            new StoreProduct("P103", "Monitor", 5200, 5),
            new StoreProduct("P104", "USB Hub", 450, 22),
            new StoreProduct("P105", "Webcam", 1200, 3),
            new StoreProduct("P106", "Headset", 1850, 11),
            new StoreProduct("P107", "Laptop Stand", 890, 15),
            new StoreProduct("P108", "Gaming Mouse", 1800, 2),
            new StoreProduct("P109", "Cable", 150, 40),
            new StoreProduct("P110", "Docking Station", 3400, 6)
        };
    }

    public static void runMode(
        StoreProduct[] original,
        String field,
        String direction
    ) {
        StoreProduct[] working = copyOf(original);

        if (field.equals("價格") && direction.equals("升冪")) {
            insertionSortByPriceAscending(working);
        } else if (field.equals("價格") && direction.equals("降冪")) {
            selectionSortByPriceDescending(working);
        } else {
            selectionSortByStockDescending(working);
        }

        System.out.println();
        System.out.println("=== 排序欄位：" + field
            + "｜排序方向：" + direction + " ===");
        printProducts(working);
    }

    public static StoreProduct[] copyOf(StoreProduct[] source) {
        StoreProduct[] result = new StoreProduct[source.length];

        for (int index = 0; index < source.length; index++) {
            result[index] = source[index];
        }
        return result;
    }

    public static void insertionSortByPriceAscending(
        StoreProduct[] values
    ) {
        for (int index = 1; index < values.length; index++) {
            StoreProduct key = values[index];
            int position = index - 1;

            while (position >= 0 &&
                   values[position].getPrice() > key.getPrice()) {
                values[position + 1] = values[position];
                position--;
            }
            values[position + 1] = key;
        }
    }

    public static void selectionSortByPriceDescending(
        StoreProduct[] values
    ) {
        for (int start = 0; start < values.length - 1; start++) {
            int maxIndex = start;

            for (int index = start + 1; index < values.length; index++) {
                if (values[index].getPrice() >
                    values[maxIndex].getPrice()) {
                    maxIndex = index;
                }
            }

            StoreProduct temp = values[start];
            values[start] = values[maxIndex];
            values[maxIndex] = temp;
        }
    }

    public static void selectionSortByStockDescending(
        StoreProduct[] values
    ) {
        for (int start = 0; start < values.length - 1; start++) {
            int maxIndex = start;

            for (int index = start + 1; index < values.length; index++) {
                if (values[index].getStock() >
                    values[maxIndex].getStock()) {
                    maxIndex = index;
                }
            }

            StoreProduct temp = values[start];
            values[start] = values[maxIndex];
            values[maxIndex] = temp;
        }
    }

    public static void printProducts(StoreProduct[] values) {
        if (values == null || values.length == 0) {
            System.out.println("目前沒有商品資料");
            return;
        }

        for (StoreProduct product : values) {
            System.out.println(product);
        }
    }
}
