import java.util.ArrayList;

public class Q12_InventoryCatalog {
    private ArrayList<Q12_Product> products = new ArrayList<>();

    public boolean addProduct(Q12_Product product) {
        if (product == null) {
            return false;
        }

        String id = product.getId();
        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        String target = id.trim();
        for (Q12_Product current : products) {
            if (current.getId().equalsIgnoreCase(target)) {
                return false;
            }
        }

        products.add(product);
        return true;
    }

    public Q12_Product[] createSortedCopyById() {
        Q12_Product[] copy = new Q12_Product[products.size()];
        for (int index = 0; index < products.size(); index++) {
            copy[index] = products.get(index);
        }

        if (copy.length < 2) {
            return copy;
        }

        Q12_Product[] temp = new Q12_Product[copy.length];
        mergeSort(copy, temp, 0, copy.length - 1);
        return copy;
    }

    private void mergeSort(
        Q12_Product[] data,
        Q12_Product[] temp,
        int left,
        int right
    ) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(data, temp, left, mid);
        mergeSort(data, temp, mid + 1, right);
        merge(data, temp, left, mid, right);
    }

    private void merge(
        Q12_Product[] data,
        Q12_Product[] temp,
        int left,
        int mid,
        int right
    ) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        int tempIndex = left;

        while (leftIndex <= mid && rightIndex <= right) {
            String leftId = data[leftIndex].getId().toLowerCase();
            String rightId = data[rightIndex].getId().toLowerCase();

            if (leftId.compareTo(rightId) <= 0) {
                temp[tempIndex] = data[leftIndex];
                leftIndex++;
            } else {
                temp[tempIndex] = data[rightIndex];
                rightIndex++;
            }
            tempIndex++;
        }

        while (leftIndex <= mid) {
            temp[tempIndex] = data[leftIndex];
            tempIndex++;
            leftIndex++;
        }

        while (rightIndex <= right) {
            temp[tempIndex] = data[rightIndex];
            tempIndex++;
            rightIndex++;
        }

        for (int index = left; index <= right; index++) {
            data[index] = temp[index];
        }
    }

    public Q12_Product binarySearchById(
        Q12_Product[] sortedProducts,
        String id
    ) {
        if (sortedProducts == null || id == null) {
            return null;
        }

        String target = id.trim().toLowerCase();
        if (target.isEmpty()) {
            return null;
        }

        int low = 0;
        int high = sortedProducts.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            String currentId =
                sortedProducts[mid].getId().toLowerCase();
            int comparison = target.compareTo(currentId);

            if (comparison == 0) {
                return sortedProducts[mid];
            } else if (comparison < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return null;
    }

    public ArrayList<Q12_Product> findByNameKeyword(
        String keyword
    ) {
        ArrayList<Q12_Product> results = new ArrayList<>();

        if (keyword == null) {
            return results;
        }

        String normalized = keyword.trim().toLowerCase();
        if (normalized.isEmpty()) {
            return results;
        }

        for (Q12_Product product : products) {
            if (product.getName().toLowerCase()
                    .contains(normalized)) {
                results.add(product);
            }
        }
        return results;
    }

    public ArrayList<Q12_Product> findLowStock(
        int maximumStock
    ) {
        ArrayList<Q12_Product> results = new ArrayList<>();

        for (Q12_Product product : products) {
            if (product.getStock() <= maximumStock) {
                results.add(product);
            }
        }
        return results;
    }

    public int totalInventoryValue() {
        int total = 0;

        for (Q12_Product product : products) {
            total += product.getPrice() * product.getStock();
        }
        return total;
    }
}

class Q12_Product {
    private String id;
    private String name;
    private int price;
    private int stock;

    public Q12_Product(
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
        return id + " " + name +
            " price=" + price + " stock=" + stock;
    }
}

class Q12_InventoryDemo {
    public static void main(String[] args) {
        Q12_InventoryCatalog catalog =
            new Q12_InventoryCatalog();

        catalog.addProduct(
            new Q12_Product("P205", "Wireless Mouse", 650, 4)
        );
        catalog.addProduct(
            new Q12_Product("P101", "Keyboard", 1200, 8)
        );
        catalog.addProduct(
            new Q12_Product("P330", "Gaming Mouse", 1800, 2)
        );
        catalog.addProduct(
            new Q12_Product("P150", "Monitor", 5200, 5)
        );

        Q12_Product[] sorted = catalog.createSortedCopyById();
        System.out.println("依編號排序：");
        for (Q12_Product product : sorted) {
            System.out.println(product);
        }

        System.out.println("查詢 P150：" +
            catalog.binarySearchById(sorted, "p150"));
        System.out.println("名稱包含 mouse：" +
            catalog.findByNameKeyword("mouse"));
        System.out.println("低庫存：" +
            catalog.findLowStock(4));
        System.out.println("庫存總值：" +
            catalog.totalInventoryValue());
    }
}
