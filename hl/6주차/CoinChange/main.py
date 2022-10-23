class Solution:
    def coinChange(self, coins: list[int], amount: int) -> int:

        n = len(coins)

        # 최소 동전 개수
        dp = [float('inf')] * (amount+1)
        dp[0] = 0

        # 1,2,...n원 만들때 필요한 동전 개수 갱신
        for i in range(amount+1):
            cases = [dp[i]]
            for j in range(n):
                if i-coins[j] >= 0:
                    cases.append(dp[i-coins[j]]+1)
            if cases:
                dp[i] = min(cases)

        if dp[amount] == float('inf'):
            return -1
        return dp[amount]

solution = Solution()
test = solution.coinChange([1,2,5], 11)