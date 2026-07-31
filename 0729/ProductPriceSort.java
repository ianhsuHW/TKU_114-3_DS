// Product 類別改為共用同資料夾的 Product.java，
// 排序邏輯與講義概念 9 相同。
public class ProductPriceSort {
    public static void main(String[] args) {
        Product[] products = {
            new Product("P103", "Keyboard", 1290),
            new Product("P205", "Mouse", 650),
            new Product("P118", "Monitor", 5200),
            new Product("P310", "Webcam", 1290)
        };

        insertionSortByPrice(products);

        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static void insertionSortByPrice(Product[] products) {
        for (int index = 1; index < products.length; index++) {
            Product key = products[index];
            int position = index - 1;

            while (position >= 0 &&
                   products[position].getPrice() > key.getPrice()) {
                products[position + 1] = products[position];
                position--;
            }
            products[position + 1] = key;
        }
    }
}
