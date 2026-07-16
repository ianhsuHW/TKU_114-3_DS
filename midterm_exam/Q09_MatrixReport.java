public class Q09_MatrixReport {
    public static void main(String[] args) {
        int[][] data = {{5, 8, 2}, {9, 4, 7}, {3, 6, 10}};
        System.out.println("第 1 列總和：" + rowSum(data, 1));
        System.out.println("第 2 欄總和：" + columnSum(data, 2));
        System.out.println("大於等於 7 的筆數：" + countAtLeast(data, 7));
        System.out.println("總和最大的列索引：" + findRowWithLargestTotal(data));
    }
    public static int rowSum(int[][] data, int row) {
        if (data == null || row < 0 || row >= data.length || data[row] == null) return -1;
        int sum = 0; for (int value : data[row]) sum += value; return sum;
    }
    public static int columnSum(int[][] data, int column) {
        if (data == null || column < 0) return -1;
        int sum = 0;
        for (int[] row : data) { if (row == null || column >= row.length) return -1; sum += row[column]; }
        return sum;
    }
    public static int countAtLeast(int[][] data, int target) {
        if (data == null) return 0;
        int count = 0;
        for (int[] row : data) if (row != null) for (int value : row) if (value >= target) count++;
        return count;
    }
    public static int findRowWithLargestTotal(int[][] data) {
        if (data == null || data.length == 0) return -1;
        int largestRow = 0, largestTotal = rowSum(data, 0);
        for (int i = 1; i < data.length; i++) { int total = rowSum(data, i); if (total > largestTotal) { largestTotal = total; largestRow = i; } }
        return largestRow;
    }
}
