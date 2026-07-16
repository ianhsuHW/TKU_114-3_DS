import java.util.Scanner;

public class SalesMatrix {
    public static void inputSales(Scanner sc, int[][] sales) {
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                int value;
                do {
                    System.out.print("商品 " + (i + 1) + " 第 " + (j + 1) + " 天銷售量：");
                    value = sc.nextInt();
                } while (value < 0);
                sales[i][j] = value;
            }
        }
    }

    public static void printTable(int[][] sales) {
        System.out.println("\n=== 銷售矩陣 ===");
        System.out.print("商品\\天\t");
        for (int j = 1; j <= sales[0].length; j++) {
            System.out.print("第" + j + "天\t");
        }
        System.out.println();

        for (int i = 0; i < sales.length; i++) {
            System.out.print("商品" + (i + 1) + "\t");
            for (int j = 0; j < sales[i].length; j++) {
                System.out.print(sales[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void printProductTotal(int[][] sales) {
        System.out.println("\n=== 每項商品銷售總量 ===");
        for (int i = 0; i < sales.length; i++) {
            int total = 0;
            for (int j = 0; j < sales[i].length; j++) {
                total += sales[i][j];
            }
            System.out.println("商品 " + (i + 1) + "：" + total);
        }
    }

    public static void printDayTotal(int[][] sales) {
        System.out.println("\n=== 每天全部商品銷售總量 ===");
        for (int j = 0; j < sales[0].length; j++) {
            int total = 0;
            for (int i = 0; i < sales.length; i++) {
                total += sales[i][j];
            }
            System.out.println("第 " + (j + 1) + " 天：" + total);
        }
    }

    public static void findMaxProduct(int[][] sales) {
        System.out.println("\n=== 銷售總量最高商品 ===");
        int maxProduct = 0;
        int maxTotal = 0;

        for (int i = 0; i < sales.length; i++) {
            int total = 0;
            for (int j = 0; j < sales[i].length; j++) {
                total += sales[i][j];
            }
            if (total > maxTotal) {
                maxTotal = total;
                maxProduct = i;
            }
        }
        System.out.println("商品 " + (maxProduct + 1) + "：" + maxTotal);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] sales = new int[3][4];

        inputSales(sc, sales);
        printTable(sales);
        printProductTotal(sales);
        printDayTotal(sales);
        findMaxProduct(sales);

        sc.close();
    }
}
