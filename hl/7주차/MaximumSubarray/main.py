class Solution:
    def maxSubArray(self, nums: list[int]) -> int:
        
        n = len(nums)
        dp = [0] * n
        dp[0] = nums[0]
        for i in range(1, n):
            num1 = nums[i]              # 현재거
            num2 = nums[i] + dp[i-1]    # 이전까지 최대 + 현재거
            dp[i] = max(num1, num2)
        
        return max(dp)
            



nums = [-2,1,-3,4,-1,2,1,-5,4]
solution = Solution()
solution.maxSubArray(nums)