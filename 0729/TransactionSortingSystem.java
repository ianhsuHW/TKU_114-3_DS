public class TransactionSortingSystem {
    public static void main(String[] args) {
        Transaction[] transactions = {
            new Transaction("T001", "A-1001", 12000, 5),
            new Transaction("T002", "A-1002", 3500, 2),
            new Transaction("T003", "A-1003", 12000, 1),
            new Transaction("T004", "A-1001", 890, 7),
            new Transaction("T005", "A-1004", 7600, 3),
            new Transaction("T006", "A-1002", 12000, 9),
            new Transaction("T007", "A-1005", 3500, 4)
        };

        System.out.println("=== 排序前 ===");
        printAll(transactions);

        insertionSortByAmount(transactions);

        System.out.println();
        System.out.println("=== 排序後（金額降冪，金額相同時時間序號升冪）===");
        printAll(transactions);

        System.out.println();
        System.out.println("=== 相同金額檢查（金額 12000）===");
        printSameAmount(transactions, 12000);

        System.out.println();
        System.out.println("=== 邊界測試 ===");
        Transaction[] empty = new Transaction[0];
        insertionSortByAmount(empty);
        System.out.println("空陣列排序完成，筆數：" + empty.length);

        Transaction[] single = {
            new Transaction("T100", "A-2001", 500, 1)
        };
        insertionSortByAmount(single);
        System.out.println("單筆排序完成：" + single[0]);
    }

    public static void insertionSortByAmount(Transaction[] values) {
        if (values == null || values.length < 2) {
            return;
        }

        for (int index = 1; index < values.length; index++) {
            Transaction key = values[index];
            int position = index - 1;

            while (position >= 0 &&
                   shouldMoveRight(values[position], key)) {
                values[position + 1] = values[position];
                position--;
            }
            values[position + 1] = key;
        }
    }

    public static boolean shouldMoveRight(
        Transaction left,
        Transaction key
    ) {
        if (left.getAmount() < key.getAmount()) {
            return true;
        }
        if (left.getAmount() > key.getAmount()) {
            return false;
        }
        return left.getTimeSequence() > key.getTimeSequence();
    }

    public static void printAll(Transaction[] values) {
        if (values == null || values.length == 0) {
            System.out.println("目前沒有交易資料");
            return;
        }

        for (Transaction transaction : values) {
            System.out.println(transaction);
        }
    }

    public static void printSameAmount(
        Transaction[] values,
        int amount
    ) {
        int found = 0;

        for (Transaction transaction : values) {
            if (transaction.getAmount() == amount) {
                System.out.println(transaction);
                found++;
            }
        }

        if (found == 0) {
            System.out.println("沒有金額為 " + amount + " 的交易");
        } else {
            System.out.println("共 " + found
                + " 筆，時間序號由小到大排列");
        }
    }
}
