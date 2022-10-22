import java.util.Arrays;

public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        final int MAX = 10001;

        Arrays.fill(dp[0], MAX);
        dp[0][0] = 0;

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];

                if (coins[i - 1] <= j) {
                    dp[i][j] = Math.min(dp[i][j  - coins[i - 1]] + 1, dp[i][j]);
                }
            }
        }

        return dp[coins.length][amount] == MAX ? -1 : dp[coins.length][amount];
    }

}