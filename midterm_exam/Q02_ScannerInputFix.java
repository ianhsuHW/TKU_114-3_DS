import java.util.Scanner;

public class Q02_ScannerInputFix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter course name: ");
        String courseName = sc.nextLine();
        System.out.print("Enter price: ");
        int price = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter note: ");
        String note = sc.nextLine();
        System.out.println("=== Order Summary ===");
        System.out.println("Course: " + courseName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Total: " + quantity * price);
        System.out.println("Note: " + note);
        sc.close();
    }
}
