package solutions.stockprofit;

class StockProfitService {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return -1;
        }

        int minPrice = prices[0];
        int maxProfit = 0;

        for (int currentPrice : prices) {
            int potentialProfit = currentPrice - minPrice;

            maxProfit = Math.max(maxProfit, potentialProfit);
            minPrice = Math.min(minPrice, currentPrice);
        }

        return maxProfit > 0 ? maxProfit : -1;
    }

    public static void main(String[] args) {
        StockProfitService solution = new StockProfitService();

        int[] prices1 = {44, 30, 24, 32, 35, 30, 40, 38, 15};
        System.out.println(solution.maxProfit(prices1));

        int[] prices2 = {10, 9, 8, 2};
        System.out.println(solution.maxProfit(prices2));

        int[] prices3 = {5, 5, 5, 5};
        System.out.println(solution.maxProfit(prices3));

        int[] prices4 = {7, 1, 5, 3, 6, 4};
        System.out.println(solution.maxProfit(prices4));
    }
}
