package w0721;

import java.util.ArrayList;
import java.util.Scanner;

public class DynamicScoreManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> scores = new ArrayList<>();
        while (true) {
            System.out.print("輸入成績（-1 結束）：");
            if (!scanner.hasNextInt()) { System.out.println("請輸入整數。"); scanner.next(); continue; }
            int score = scanner.nextInt();
            if (score == -1) break;
            if (score < 0 || score > 100) System.out.println("成績必須介於 0 到 100。 ");
            else scores.add(score);
        }
        printReport(scores);
    }
    public static double average(ArrayList<Integer> scores) { int total = 0; for (int s : scores) total += s; return scores.isEmpty() ? 0 : (double) total / scores.size(); }
    public static ArrayList<Integer> passingScores(ArrayList<Integer> scores) { ArrayList<Integer> result = new ArrayList<>(); for (int s : scores) if (s >= 60) result.add(s); return result; }
    public static void printReport(ArrayList<Integer> scores) {
        if (scores.isEmpty()) { System.out.println("沒有有效成績。 "); return; }
        int max = scores.get(0), min = scores.get(0);
        for (int s : scores) { max = Math.max(max, s); min = Math.min(min, s); }
        System.out.println("筆數：" + scores.size() + "，平均：" + average(scores) + "，最高：" + max + "，最低：" + min);
        System.out.println("及格名單：" + passingScores(scores));
    }
}
