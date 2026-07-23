package w0721;

import java.util.ArrayList;

public class ArrayVsArrayListDemo {
    public static void main(String[] args) {
        int[] fixedScores = {80, 90, 75};
        ArrayList<Integer> dynamicScores = new ArrayList<>();
        dynamicScores.add(80);
        dynamicScores.add(90);
        dynamicScores.add(75);
        dynamicScores.add(88);

        System.out.println("陣列長度：" + fixedScores.length);
        System.out.println("ArrayList 大小：" + dynamicScores.size());
        System.out.println("動態成績：" + dynamicScores);
    }
}
