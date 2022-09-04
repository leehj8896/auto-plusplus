# 입력 받기
H, W = map(int, input().split())
board = []
for _ in range(H):
    row = list(map(int, input()))
    board.append(row)

from collections import deque
q = deque()
q.append([0,0,0])

# 부순적없는거리, 부수고온거리
dist = [[[float('inf'), float('inf')] for _ in range(W)] for _ in range(H)]
dist[0][0] = [1, 1]

dy, dx = [0,0,-1,1], [1,-1,0,0]
while q:
    cy, cx, count = q.popleft()
    for i in range(4):
        ny, nx = cy + dy[i], cx + dx[i]
        if 0 <= ny < H and 0 <= nx < W:
            # 다음 노드가 빈칸일 경우
            if board[ny][nx] == 0:
                # 방문한적 없으면
                if dist[ny][nx][count] == float('inf'):
                    dist[ny][nx][count] = dist[cy][cx][count] + 1
                    q.append([ny, nx, count])
            # 벽일 경우
            elif board[ny][nx] == 1:
                # 부순적 없으면
                if count == 0:
                    # 방문한적 없으면
                    if dist[ny][nx][count+1] == float('inf'):
                        dist[ny][nx][count+1] = dist[cy][cx][count] + 1
                        q.append([ny, nx, count+1])

if min(dist[-1][-1]) == float('inf'):
    print(-1)
else:
    print(min(dist[-1][-1]))
