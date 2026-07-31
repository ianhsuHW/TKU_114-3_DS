public class ScoreRankingPractice {
    public static void main(String[] args) {
        // 至少 8 筆成績，含相同分數
        int[] scores = {72, 95, 58, 88, 95, 61, 88, 79, 45};

        System.out.println("=== 成績降冪排名 ===");
        System.out.println("（不使用 Arrays.sort()，自行實作 Selection Sort）");
        System.out.println();

        selectionSortDescending(scores);
        printRanking(scores);
    }

    public static void selectionSortDescending(int[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int maxIndex = start;

            for (int index = start + 1; index < values.length; index++) {
                if (values[index] > values[maxIndex]) {
                    maxIndex = index;
                }
            }

            int temp = values[start];
            values[start] = values[maxIndex];
            values[maxIndex] = temp;
        }
    }

    public static void printRanking(int[] values) {
        if (values.length == 0) {
            System.out.println("目前沒有成績資料");
            return;
        }

        System.out.printf("%6s %8s %8s%n", "名次", "分數", "是否及格");

        int rank = 1;

        for (int index = 0; index < values.length; index++) {
            // 分數相同時名次相同；不同分數時名次跳到目前位置
            if (index > 0 && values[index] != values[index - 1]) {
                rank = index + 1;
            }

            String passed;
            if (values[index] >= 60) {
                passed = "及格";
            } else {
                passed = "不及格";
            }

            System.out.printf("%6d %8d %8s%n", rank, values[index], passed);
        }
    }
}
