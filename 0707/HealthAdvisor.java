import java.util.*;

public class HealthAdvisor {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String yn;//放在loop內while讀不到
        do{//ai教do while迴圈 等於while迴圈 但至少執行一次
        
        System.out.print("請輸入姓名:");
        String name = scn.nextLine();

        System.out.print("請輸入身高(公尺):");
        double height = scn.nextDouble();
        
        System.out.print("請輸入體重(公斤):");
        double weight = scn.nextDouble();





        double BMI =  weight / (height * height);
        System.out.println("姓名: " + name);
        System.out.println("BMI: " + BMI);
        
        if (BMI < 18.5) {
            System.out.println("Underweight");
        } else if ( BMI < 24) {
            System.out.println("Normal weight");
        }else if ( BMI <27) {
            System.out.println("Overweight");
        } else {
            System.out.println("Obese");
        }

        System.out.println("是否繼續輸入下一筆？(y/n)：" );
         yn = scn.next();
         scn.nextLine();//ai教避免姓名輸入時被吃掉
    }while(yn.equalsIgnoreCase("y"));//ai教原:yn.equals改 yn.equalsIgnoreCase 忽視大小寫
    scn.close();
}
}
