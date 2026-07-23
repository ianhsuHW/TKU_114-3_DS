package w0721;

import java.util.ArrayList;

public class ArrayListSearchDemo {
    public static void main(String[] args) {
        ArrayList<String> courses = new ArrayList<>();
        courses.add("Java");
        courses.add("SQL");
        courses.add("Web");
        String keyword = "SQL";
        System.out.println("是否存在：" + courses.contains(keyword));
        System.out.println("所在索引：" + courses.indexOf(keyword));
        System.out.println("找不到的結果：" + courses.indexOf("Python"));
    }
}
