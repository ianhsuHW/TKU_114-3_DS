import java.util.ArrayList;

public class LibraryManagementSystem {
    private ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();

        System.out.println("=== 空資料測試 ===");
        library.printAll();
        library.searchById("B101");
        System.out.println("分類查詢：" +
            BookAlgorithms.findByCategory(library.books, "程式設計"));

        System.out.println();
        System.out.println("=== 新增書籍 ===");
        System.out.println("B205 " + library.addBook(
            new Book("B205", "Java 基礎", "程式設計", 12)));
        System.out.println("B101 " + library.addBook(
            new Book("B101", "資料結構", "程式設計", 25)));
        System.out.println("B330 " + library.addBook(
            new Book("B330", "統計學導論", "數學", 8)));
        System.out.println("B150 " + library.addBook(
            new Book("B150", "演算法設計", "程式設計", 25)));
        System.out.println("B410 " + library.addBook(
            new Book("B410", "線性代數", "數學", 15)));
        System.out.println("b101（重複編號）" + library.addBook(
            new Book("b101", "重複書籍", "程式設計", 1)));
        System.out.println("null " + library.addBook(null));
        System.out.println("空編號 " + library.addBook(
            new Book("   ", "無編號", "其他", 0)));

        System.out.println();
        System.out.println("=== 全部書籍（ArrayList 原始順序）===");
        library.printAll();

        System.out.println();
        System.out.println("=== 依編號升冪（Merge Sort）===");
        Book[] byId = library.createSortedById();
        printArray(byId);

        System.out.println();
        System.out.println("=== 依借閱次數降冪（Merge Sort，相同次數保持原順序）===");
        Book[] byBorrow = library.createSortedByBorrowCount();
        printArray(byBorrow);

        System.out.println();
        System.out.println("=== Binary Search 依編號查詢 ===");
        library.searchById("B150");
        library.searchById("b410");
        library.searchById("B999");
        library.searchById(null);

        System.out.println();
        System.out.println("=== Sequential Search 依分類查詢 ===");
        library.printByCategory("程式設計");
        library.printByCategory("數學");
        library.printByCategory("文學");

        System.out.println();
        System.out.println("=== 確認原始順序未被排序改變 ===");
        library.printAll();
    }

    public boolean addBook(Book book) {
        if (book == null) {
            return false;
        }

        String id = book.getId();
        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        String target = id.trim();
        for (Book current : books) {
            if (current.getId().equalsIgnoreCase(target)) {
                return false;
            }
        }

        books.add(book);
        return true;
    }

    public Book[] createSortedById() {
        Book[] copy = BookAlgorithms.toArray(books);
        BookAlgorithms.mergeSortById(copy);
        return copy;
    }

    public Book[] createSortedByBorrowCount() {
        Book[] copy = BookAlgorithms.toArray(books);
        BookAlgorithms.mergeSortByBorrowCount(copy);
        return copy;
    }

    public void searchById(String id) {
        if (books.isEmpty()) {
            System.out.println("查詢 " + id + "：目前沒有書籍資料");
            return;
        }

        Book[] sorted = createSortedById();
        int index = BookAlgorithms.binarySearchById(sorted, id);

        if (index == -1) {
            System.out.println("查詢 " + id + "：查無此書");
        } else {
            System.out.println("查詢 " + id + "：找到 " + sorted[index]);
        }
    }

    public void printByCategory(String category) {
        ArrayList<Book> results =
            BookAlgorithms.findByCategory(books, category);

        if (results.isEmpty()) {
            System.out.println("分類 " + category + "：沒有符合的書籍");
            return;
        }

        System.out.println("分類 " + category + "：共 "
            + results.size() + " 筆");
        for (Book book : results) {
            System.out.println("  " + book);
        }
    }

    public void printAll() {
        if (books.isEmpty()) {
            System.out.println("目前沒有書籍資料");
            return;
        }

        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void printArray(Book[] values) {
        if (values == null || values.length == 0) {
            System.out.println("沒有資料");
            return;
        }

        for (Book book : values) {
            System.out.println(book);
        }
    }
}
