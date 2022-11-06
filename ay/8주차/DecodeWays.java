public class DecodeWays {
    public int numDecodings(String s) {
        int[] nums = new int[s.length() + 1];
        int[] dp = new int[s.length() + 1];

        dp[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            nums[i] = s.charAt(i - 1) - '0';

            // 1 ~ 9 가능
            if (nums[i] != 0) {
                dp[i] += dp[i - 1];
            }

            // 10 ~ 26 가능
            int num = nums[i - 1] * 10 + nums[i];
            if (num >= 10 && num <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[s.length()];
    }
}