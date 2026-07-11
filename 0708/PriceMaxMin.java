public class PriceMaxMin {
    public static void main(String[] args) {
        int[] prices = {30, 45, 60};

        int max = prices[0];
        int min = prices[0];

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > max) {
                max = prices[i];
            }

            if (prices[i] < min) {
                min = prices[i];
            }
        }

        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
    }
}
