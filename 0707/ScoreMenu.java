import java.util.*;

public class ScoreMenu {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.print("輸入姓名：");
        String name = scn.nextLine();

        System.out.print("輸入 Java 成績：");
        double javaScore = scn.nextDouble();

        System.out.print("輸入 English 成績：");
        double englishScore = scn.nextDouble();

        System.out.print("輸入 Math 成績：");
        double mathScore = scn.nextDouble();

        double avg = (javaScore + englishScore + mathScore) / 3.0;

        String status = (avg >= 60) ? "及格" : "不及格";//+0教三元運算子 條件ok前者 非則後者

        String grade;
        if (avg >= 90) {
            grade = "A";
        } else if (avg >= 80) {
            grade = "B";
        } else if (avg >= 70) {
            grade = "C";
        } else if (avg >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        int choice = -1;


        while (choice != 0) {
            System.out.println("\n--- 選單 ---");
            System.out.println("1：顯示平均分數");
            System.out.println("2：顯示及格狀態");
            System.out.println("3：顯示等第");
            System.out.println("0：離開");
            System.out.print("請選擇操作 (0-3)：");
            
            choice = scn.nextInt();


            switch (choice) {
                case 1:
                    System.out.println("【" + name + "】的平均分數為：" + avg);
                    break;
                case 2:
                    System.out.println("【" + name + "】的及格狀態為：" + status);
                    break;
                case 3:
                    System.out.println("【" + name + "】的等第為：" + grade);
                    break;
                case 0:
                    System.out.println("離開");
                    break;
                default:
                    System.out.println("輸入錯誤！");
                    break;
            }
        }

        scn.close();
    }
}