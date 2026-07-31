public class RecursiveDigitSumPractice {
    public static void main(String[] args) {
        System.out.println("=== 遞迴計算各位數總和 ===");

        report(5729);
        report(0);
        report(7);
        report(1000);
        report(99999);
        report(123456);

        System.out.println();
        System.out.println("手算驗證：5729 -> 5+7+2+9 = 23");
    }

    public static void report(int number) {
        System.out.printf(
            "digitSum(%d) = %d%n",
            number, digitSum(number)
        );
    }

    public static int digitSum(int number) {
        if (number < 0) {
            return digitSum(-number);
        }

        // base case：只剩一位數時直接回傳，可確實到達。
        if (number < 10) {
            return number;
        }

        // recursive case：每次去掉個位數，數字必定變小。
        return number % 10 + digitSum(number / 10);
    }
}
