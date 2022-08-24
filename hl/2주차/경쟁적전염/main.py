'''
그리디: 가장 가까운 좌표들 중 가장 작은 수가 가장 먼저 만나는 바이러스일 것이다.
BFS: 거리 계산할 때
'''
from collections import deque

# 입력 받기
N, K = map(int, input().split())
board = []
for _ in range(N):
    row = list(map(int, input().split()))
    board.append(row)
S, Y, X = map(int, input().split())

# BFS 초기화
dy, dx = [0,0,-1,1], [1,-1,0,0]
sy, sx = Y-1, X-1
visited = [[False for _ in range(N)] for _ in range(N)]
positions = [[] for _ in range(N*2)]    # 거리별 좌표 리스트

# BFS
q = deque()
q.append([sy, sx, 0])
visited[sy][sx] = True
while q:
    cy, cx, dist = q.popleft()
    positions[dist].append([cy,cx])     # 거리별 좌표 push
    for i in range(4):
        ny, nx = cy + dy[i], cx + dx[i]
        if 0 <= ny < N and 0 <= nx < N:
            if not visited[ny][nx]:
                visited[ny][nx] = True
                q.append([ny, nx, dist+1])

# 시간 내 만나는 좌표 중 최소 바이러스 찾기
for dist in range(S+1):
    curr_positions = positions[dist]
    min_virus = float('inf')
    for y, x in curr_positions:
        if board[y][x] != 0:
            min_virus = min(min_virus, board[y][x])
    # 거리별 좌표 중 바이러스 존재 & 최소 갱신했을 경우
    if min_virus != float('inf'):
        break

# 시간내 만나는 바이러스 없을 경우
if min_virus == float('inf'):
    print(board[sy][sx])
else:
    print(min_virus)