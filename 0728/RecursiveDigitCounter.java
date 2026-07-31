public class RecursiveDigitCounter {
    public static void main(String[] args) {
        System.out.println("=== 遞迴統計數字出現次數 ===");

        report(122333, 3);
        report(5, 5);
        report(0, 0);
        report(-4404, 4);
        report(98765, 1);
        report(1111, 1);
        report(1020304, 0);
        report(123, 15);

        System.out.println();
        System.out.println("說明：target 必須介於 0 到 9，超出範圍回傳 -1。");
    }

    public static void report(int number, int target) {
        int count = countDigit(number, target);

        if (count == -1) {
            System.out.printf(
                "number=%d, target=%d：target 超出 0～9，無法統計%n",
                number, target
            );
        } else {
            System.out.printf(
                "number=%d, target=%d：出現 %d 次%n",
                number, target, count
            );
        }
    }

    public static int countDigit(int number, int target) {
        if (target < 0 || target > 9) {
            return -1;
        }

        if (number < 0) {
            return countDigit(-number, target);
        }

        if (number < 10) {
            if (number == target) {
                return 1;
            }
            return 0;
        }

        int current = 0;
        if (number % 10 == target) {
            current = 1;
        }

        return current + countDigit(number / 10, target);
    }
}
