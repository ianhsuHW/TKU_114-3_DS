public class Q07_ArrayAudit {
    private static final int MIN_VALID = 10;
    private static final int MAX_VALID = 60;
    private static final int INVALID_MARK = -1;

    public static void main(String[] args) {
        int[] readings = {12, 71, 35, -4, 35, 22, 60, 9, 48, 61};
        System.out.println("有效筆數：" + countValid(readings));
        System.out.printf("有效平均：%.2f%n", averageValid(readings));
        System.out.println("最後符合門檻的索引：" + findLastAtLeast(readings, 35));
        System.out.print("清理後資料：");
        printArray(createCleanCopy(readings));
        System.out.print("原始資料：");
        printArray(readings);
    }

    private static boolean isValid(int value) { return value >= MIN_VALID && value <= MAX_VALID; }
    public static int countValid(int[] data) {
        int count = 0;
        for (int value : data) if (isValid(value)) count++;
        return count;
    }
    public static double averageValid(int[] data) {
        int count = 0, sum = 0;
        for (int value : data) if (isValid(value)) { sum += value; count++; }
        return count == 0 ? -1.0 : (double) sum / count;
    }
    public static int findLastAtLeast(int[] data, int target) {
        for (int i = data.length - 1; i >= 0; i--) if (isValid(data[i]) && data[i] >= target) return i;
        return -1;
    }
    public static int[] createCleanCopy(int[] data) {
        int[] copy = new int[data.length];
        for (int i = 0; i < data.length; i++) copy[i] = isValid(data[i]) ? data[i] : INVALID_MARK;
        return copy;
    }
    private static void printArray(int[] data) {
        System.out.print("[");
        for (int i = 0; i < data.length; i++) System.out.print(data[i] + (i + 1 == data.length ? "]\n" : ", "));
    }
}
