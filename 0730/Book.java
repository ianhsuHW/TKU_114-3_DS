public class Book {
    private String id;
    private String title;
    private String category;
    private int borrowCount;

    public Book(
        String id,
        String title,
        String category,
        int borrowCount
    ) {
        if (id == null) {
            this.id = "";
        } else {
            this.id = id.trim();
        }

        if (title == null) {
            this.title = "";
        } else {
            this.title = title.trim();
        }

        if (category == null) {
            this.category = "";
        } else {
            this.category = category.trim();
        }

        if (borrowCount < 0) {
            this.borrowCount = 0;
        } else {
            this.borrowCount = borrowCount;
        }
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    @Override
    public String toString() {
        return id + " " + title
            + " 分類=" + category
            + " 借閱=" + borrowCount;
    }
}
