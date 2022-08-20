'''
N: 앱 개수
M: 추가로 필요한 메모리
'''
N, M = map(int, input().split())
memory = [0] + list(map(int, input().split()))
cost = [0] + list(map(int, input().split()))

'''
특정 비용으로 얻을 수 있는 메모리 최대값
(앱개수 * 총비용) 사이즈
'''
총비용 = sum(cost)
dp = [[0]*(총비용+1) for _ in range(N+1)]
최소비용 = 총비용

for i in range(1, N+1):
    for j in range(0, 총비용+1):
        
        # 계산하려는 비용보다 크면
        if cost[i] > j:
            dp[i][j] = dp[i-1][j]                   # 위에거
        else:
            case1 = dp[i-1][j-cost[i]] + memory[i]  # 현재 앱 포함할 경우 최대비용
            case2 = dp[i-1][j]                      # 현재 앱 포함하지 않을 경우 최대비용
            dp[i][j] = max(case1, case2)

        # 결과 기록
        if dp[i][j] >= M:
            최소비용 = min(최소비용, j)

print(최소비용)
