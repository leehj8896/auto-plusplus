'''
DP
O(N*N) (N<=20)
경우의 수 2차원 배열로 했음
다른 더 좋은 방법도 있을 것 같음
'''
# 입력 받기
N = int(input())
cost = []
for _ in range(N-1):
    small, big = map(int, input().split())  # 작은점프, 큰점프
    cost.append([small, big])
K = int(input())                            # 최고점프

# i로 올때 최고점프를 할 경우 & 최소 비용
dp = [[float('inf')] * (N) for _ in range(N)]
for i in range(N):
    dp[i][0] = 0

for i in range(N):
    for j in range(N):
        # 최고점프로 왔을 경우
        if j == i:
            if j-3 >= 0:
                dp[i][j] = dp[i][j-3] + K
        # 아닐 경우
        else:
            cases = []
            # j-1에서 점프
            if j-1 >= 0:
                cases.append(dp[i][j-1] + cost[j-1][0])
            # j-2에서 점프
            if j-2 >= 0:
                cases.append(dp[i][j-2] + cost[j-2][1])
            # 최소비용
            if cases:
                dp[i][j] = min(cases)

print(min([dp[i][-1] for i in range(N)]))