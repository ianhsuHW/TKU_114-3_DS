import java.util.Scanner;

public class BmiMethod {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("身高(m): ");
        double height = sc.nextDouble();
        System.out.print("體重(kg): ");
        double weight = sc.nextDouble();

        double bmi = calculateBmi(height, weight);
        System.out.println("BMI: " + bmi);

        sc.close();
    }

    public static double calculateBmi(double height, double weight) {
        return weight / (height * height);
    }
}
