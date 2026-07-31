public class ContestRankingSystem {
    public static void main(String[] args) {
        Contestant[] contestants = {
            new Contestant("C101", "Amy", 92, 305),
            new Contestant("C102", "Ben", 88, 280),
            new Contestant("C103", "Cara", 92, 268),
            new Contestant("C104", "Dan", 75, 410),
            new Contestant("C105", "Ella", 88, 251),
            new Contestant("C106", "Frank", 100, 330),
            new Contestant("C107", "Gina", 75, 295),
            new Contestant("C108", "Henry", 92, 305)
        };

        System.out.println("=== 排名前 ===");
        printList(contestants);

        insertionSortByRank(contestants);

        System.out.println();
        System.out.println("=== 排名結果（分數高者優先，同分則秒數少者優先）===");
        printRanking(contestants);

        System.out.println();
        System.out.println("=== 邊界測試 ===");
        Contestant[] empty = new Contestant[0];
        insertionSortByRank(empty);
        System.out.println("空陣列排序完成，筆數：" + empty.length);

        Contestant[] single = {
            new Contestant("C201", "Ivy", 60, 120)
        };
        insertionSortByRank(single);
        System.out.println("單筆排序完成：" + single[0]);
    }

    public static void insertionSortByRank(Contestant[] values) {
        if (values == null || values.length < 2) {
            return;
        }

        for (int index = 1; index < values.length; index++) {
            Contestant key = values[index];
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
        Contestant left,
        Contestant key
    ) {
        if (left.getScore() < key.getScore()) {
            return true;
        }
        if (left.getScore() > key.getScore()) {
            return false;
        }
        return left.getSeconds() > key.getSeconds();
    }

    public static void printList(Contestant[] values) {
        for (Contestant contestant : values) {
            System.out.println(contestant);
        }
    }

    public static void printRanking(Contestant[] values) {
        if (values == null || values.length == 0) {
            System.out.println("目前沒有參賽資料");
            return;
        }

        int rank = 1;

        for (int index = 0; index < values.length; index++) {
            if (index > 0 && !isSameRank(values[index - 1], values[index])) {
                rank = index + 1;
            }

            System.out.printf(
                "第 %d 名｜%s%n",
                rank,
                values[index]
            );
        }
    }

    public static boolean isSameRank(Contestant left, Contestant right) {
        return left.getScore() == right.getScore()
            && left.getSeconds() == right.getSeconds();
    }
}
