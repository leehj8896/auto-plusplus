# 같은 코든데 cpp는 되고 얘는 시간초과 나는거같음

''' 입력받기 '''
N = int(input())
graph = []
for _ in range(N):
    row = list(map(int, input().split()))
    graph.append(row)
dp = [[float('inf')] * (2 ** N) for _ in range(N)]

'''
최소 비용 리턴
ci: 현재
visted: 지금까지 방문한곳
'''
def getMinCost(ci, visited):
    ''' 종료조건 '''
    # 모두 방문했으면
    if visited == (2 ** N) - 1:
        # 시작노드로 가는 경로 있으면
        if graph[ci][0] != 0:
            return graph[ci][0]
        # 없으면
        return float('inf')

    # 이미 최소비용 계산했으면
    if dp[ci][visited] != float('inf'):
        return dp[ci][visited]

    ''' 점화식 '''
    # 다음 노드 탐색
    for ni in range(1, N):

        # 경로 없으면 패스
        if graph[ci][ni] == 0:
            continue

        # 다음 노드를 이미 방문했으면 패스 (DP)
        '''
        0001 &
        0001
        겹침    >> 1 >> 이미 방문했음
        안겹침  >> 0    >> 처음 방문
        '''
        if visited & (2 ** ni):
            continue

        # 다음 최소 비용
        nextMinCost = getMinCost(ni, visited | (2 ** ni)) + graph[ci][ni]
        # 현재 최소 비용 갱신
        if dp[ci][visited] > nextMinCost:
            dp[ci][visited] = nextMinCost

    return dp[ci][visited]

'''
초기 0에서 시작
방문 0001
'''
minCost = getMinCost(0, 1)
print(minCost)
