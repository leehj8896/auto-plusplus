class Solution:
    def numDecodings(self, s: str) -> int:
        
        n = len(s)
        dp = [[0,0] for _ in range(n)]
        if 1 <= int(s[0]) <= 9:
            dp[0][0] = 1
        for i in range(1, n):
            # 1자리로 쓰일 경우
            if 1 <= int(s[i]) <= 9:                 # 가능
                dp[i][0] = dp[i-1][0] + dp[i-1][1]
            else:                                   # 불가능
                pass
            # 2자리에 포함될 경우
            if int(s[i-1]) == 0:        # i-1번째가 0일때
                dp[i][1] = dp[i-1][0]
            else:
                if 1 <= int(s[i-1:i+1]) <= 26:      # 가능
                    dp[i][1] = dp[i-1][0]
                else:                               # 불가능
                    pass
        
        return sum(dp[-1])

    # def dfs(self, i):
    #     global count

    #     if i == len(s):
    #         count += 1

    #     if i < len(s) and 1 <= int(s[i]) <= 9:
    #         self.dfs(i+1)
    #     if i+2 <= len(s) and 10 <= int(s[i:i+2]) <= 26:
    #         self.dfs(i+2)

s = '9012'
solution = Solution()
solution.numDecodings(s)