import java.util.ArrayList;

public class BookAlgorithms {

    // ---------- Merge Sort：依編號升冪 ----------

    public static void mergeSortById(Book[] books) {
        if (books == null || books.length < 2) {
            return;
        }

        Book[] temp = new Book[books.length];
        mergeSortById(books, temp, 0, books.length - 1);
    }

    private static void mergeSortById(
        Book[] books,
        Book[] temp,
        int left,
        int right
    ) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSortById(books, temp, left, mid);
        mergeSortById(books, temp, mid + 1, right);
        mergeById(books, temp, left, mid, right);
    }

    private static void mergeById(
        Book[] books,
        Book[] temp,
        int left,
        int mid,
        int right
    ) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        int tempIndex = left;

        while (leftIndex <= mid && rightIndex <= right) {
            // 排序鍵值與 Binary Search 的搜尋鍵值必須一致，
            // 因此兩邊都統一轉成小寫再比較。
            String leftId = books[leftIndex].getId().toLowerCase();
            String rightId = books[rightIndex].getId().toLowerCase();

            if (leftId.compareTo(rightId) <= 0) {
                temp[tempIndex] = books[leftIndex];
                leftIndex++;
            } else {
                temp[tempIndex] = books[rightIndex];
                rightIndex++;
            }
            tempIndex++;
        }

        while (leftIndex <= mid) {
            temp[tempIndex] = books[leftIndex];
            tempIndex++;
            leftIndex++;
        }

        while (rightIndex <= right) {
            temp[tempIndex] = books[rightIndex];
            tempIndex++;
            rightIndex++;
        }

        for (int index = left; index <= right; index++) {
            books[index] = temp[index];
        }
    }

    // ---------- Merge Sort：依借閱次數降冪（穩定） ----------

    public static void mergeSortByBorrowCount(Book[] books) {
        if (books == null || books.length < 2) {
            return;
        }

        Book[] temp = new Book[books.length];
        mergeSortByBorrowCount(books, temp, 0, books.length - 1);
    }

    private static void mergeSortByBorrowCount(
        Book[] books,
        Book[] temp,
        int left,
        int right
    ) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSortByBorrowCount(books, temp, left, mid);
        mergeSortByBorrowCount(books, temp, mid + 1, right);
        mergeByBorrowCount(books, temp, left, mid, right);
    }

    private static void mergeByBorrowCount(
        Book[] books,
        Book[] temp,
        int left,
        int mid,
        int right
    ) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        int tempIndex = left;

        while (leftIndex <= mid && rightIndex <= right) {
            // 相同借閱次數時先取左側，維持穩定性。
            if (books[leftIndex].getBorrowCount() >=
                books[rightIndex].getBorrowCount()) {
                temp[tempIndex] = books[leftIndex];
                leftIndex++;
            } else {
                temp[tempIndex] = books[rightIndex];
                rightIndex++;
            }
            tempIndex++;
        }

        while (leftIndex <= mid) {
            temp[tempIndex] = books[leftIndex];
            tempIndex++;
            leftIndex++;
        }

        while (rightIndex <= right) {
            temp[tempIndex] = books[rightIndex];
            tempIndex++;
            rightIndex++;
        }

        for (int index = left; index <= right; index++) {
            books[index] = temp[index];
        }
    }

    // ---------- Binary Search：依編號（需已排序） ----------

    public static int binarySearchById(Book[] sortedBooks, String id) {
        if (sortedBooks == null || id == null) {
            return -1;
        }

        String target = id.trim().toLowerCase();
        if (target.isEmpty()) {
            return -1;
        }

        int low = 0;
        int high = sortedBooks.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            String currentId = sortedBooks[mid].getId().toLowerCase();
            int comparison = target.compareTo(currentId);

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    // ---------- Sequential Search：依分類找出全部 ----------

    public static ArrayList<Book> findByCategory(
        ArrayList<Book> books,
        String category
    ) {
        ArrayList<Book> results = new ArrayList<>();

        if (books == null || category == null) {
            return results;
        }

        String target = category.trim();
        if (target.isEmpty()) {
            return results;
        }

        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(target)) {
                results.add(book);
            }
        }
        return results;
    }

    // ---------- 工具 ----------

    public static Book[] toArray(ArrayList<Book> books) {
        Book[] result = new Book[books.size()];

        for (int index = 0; index < books.size(); index++) {
            result[index] = books.get(index);
        }
        return result;
    }
}
