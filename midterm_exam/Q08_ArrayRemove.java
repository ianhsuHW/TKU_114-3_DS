public class Q08_ArrayRemove {
    public static void main(String[] args) {
        int[] values = {4, 7, 2, 7, 9, 7, 1};
        int target = 7;
        System.out.println("Count: " + countOccurrences(values, target));
        System.out.println("Last index: " + findLastIndex(values, target));
        printArray(removeAll(values, target));
        printArray(values);
    }
    public static int countOccurrences(int[] data, int target) {
        int count = 0; for (int value : data) if (value == target) count++; return count;
    }
    public static int findLastIndex(int[] data, int target) {
        for (int i = data.length - 1; i >= 0; i--) if (data[i] == target) return i;
        return -1;
    }
    public static int[] removeAll(int[] data, int target) {
        int[] result = new int[data.length - countOccurrences(data, target)];
        int index = 0;
        for (int value : data) if (value != target) result[index++] = value;
        return result;
    }
    private static void printArray(int[] data) {
        System.out.print("["); for (int i = 0; i < data.length; i++) System.out.print(data[i] + (i + 1 == data.length ? "]\n" : ", "));
    }
}
