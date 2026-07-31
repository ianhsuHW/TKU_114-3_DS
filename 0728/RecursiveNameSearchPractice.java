import java.util.Arrays;

public class RecursiveNameSearchPractice {
    public static void main(String[] args) {
        String[] names = {
            "Amy", "Ben", "Cara", "Dan", "Ella", "Frank"
        };

        System.out.println("姓名陣列：" + Arrays.toString(names));
        System.out.println();

        System.out.println("=== 測試 ===");
        report(names, "Amy");
        report(names, "Frank");
        report(names, "Cara");
        report(names, "Zoe");

        System.out.println();
        System.out.println("=== 邊界測試 ===");
        report(new String[0], "Amy");
        report(new String[] {"Solo"}, "Solo");
        report(null, "Amy");
    }

    public static void report(String[] names, String target) {
        int index = search(names, target, 0);

        if (index == -1) {
            System.out.println("搜尋 " + target + "：找不到");
        } else {
            System.out.println("搜尋 " + target + "：索引 " + index);
        }
    }

    public static int search(String[] names, String target, int index) {
        // base case 一：陣列為空或已走到結尾
        if (names == null || index >= names.length) {
            return -1;
        }

        // base case 二：目前位置就是答案，字串使用 equals() 比較
        if (names[index] != null && names[index].equals(target)) {
            return index;
        }

        // recursive case：索引往後移動一格
        return search(names, target, index + 1);
    }
}
