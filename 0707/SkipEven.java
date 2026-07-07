public class SkipEven {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                continue; // Skip even. break會直接跳出
            }
            System.out.println(i);
        }
    }
}
