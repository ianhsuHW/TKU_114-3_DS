public class Q01_ParkingFeeFix {
    public static void main(String[] args) {
        int[] testMinutes = {-20, 30, 31, 60, 61, 120, 121, 420};
        for (int minutes : testMinutes) {
            System.out.println("Parking " + minutes + " minutes: " + calculateFee(minutes));
        }
    }

    public static int calculateFee(int minutes) {
        if (minutes < 0) return -1;
        if (minutes <= 30) return 0;
        if (minutes <= 120) return ((minutes - 31) / 30 + 1) * 20;
        return Math.min(180, 60 + ((minutes - 121) / 60 + 1) * 30);
    }
}
