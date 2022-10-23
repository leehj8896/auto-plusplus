class Solution:

    def missingNumber(self, nums: list[int]) -> int:
        n = len(nums)
        check = [0] * (n+1)
        for num in nums:
            check[num] = 1
        for i in range(n+1):
            if check[i] == 0:
                return i
        return -1