public class TemperatureLevel {
   
    public static void main(String[] args) {
        double temperature = 22.5;

        if (temperature < 15) {
            System.out.println("cold");
        } else if (temperature < 28) {
            System.out.println("Comfortable");

        } else {
            System.out.println("hot");
        }
    }
}

