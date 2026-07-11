import java.util.Scanner;

public class PersonalProfileApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("姓名：");
        String name = sc.nextLine();

        int age = readPositiveInt(sc, "年齡：");
        double height = readPositiveDouble(sc, "身高：");
        double weight = readPositiveDouble(sc, "體重：");

        boolean adult = isAdult(age);
        double bmi = calculateBmi(height, weight);
        String level = getBmiLevel(bmi);

        printReport(name, age, adult, height, weight, bmi, level);
        sc.close();
    }

    public static int readPositiveInt(Scanner sc, String message) {
        int value;
        while (true) {
            System.out.print(message);
            value = sc.nextInt();
            if (value > 0) {
                return value;
            }
            System.out.println("輸入錯誤，請輸入大於 0 的數字");
        }
    }

    public static double readPositiveDouble(Scanner sc, String message) {
        double value;
        while (true) {
            System.out.print(message);
            value = sc.nextDouble();
            if (value > 0) {
                return value;
            }
            System.out.println("輸入錯誤，請輸入大於 0 的數字");
        }
    }

    public static double calculateBmi(double height, double weight) {
        return weight / (height * height);
    }

    public static String getBmiLevel(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 24) {
            return "Normal";
        } else if (bmi < 27) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static boolean isAdult(int age) {
        return age >= 18;
    }

    public static void printReport(String name, int age, boolean adult, double height, double weight, double bmi, String level) {
        System.out.println();
        System.out.println("=== Report ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Adult: " + adult);
        System.out.println("Height: " + height);
        System.out.println("Weight: " + weight);
        System.out.println("BMI: " + bmi);
        System.out.println("Level: " + level);
    }
}
