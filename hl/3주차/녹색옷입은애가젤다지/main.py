'''
다익스트라 최단경로
'''
case = 1
while True:

    # 입력 받기
    import sys
    input = sys.stdin.readline
    N = int(input())
    if N == 0:
        break
    board = []
    for _ in range(N):
        row = list(map(int, input().split()))
        board.append(row)

    # 힙
    import heapq
    q = []
    heapq.heappush(q, [board[0][0], 0, 0])

    # 최소 비용 저장
    dist = [[float('inf') for _ in range(N)] for _ in range(N)]
    dist[0][0] = board[0][0]

    # 이미 처리했는지 저장 
    checked = [[0 for _ in range(N)] for _ in range(N)]

    # 최단 경로 탐색
    dy, dx = [0,0,-1,1], [1,-1,0,0]
    while q:
        curr_cost, cy, cx = heapq.heappop(q)
        # 이미 처리했으면
        if checked[cy][cx]:
            continue
        checked[cy][cx] = 1
        for i in range(4):
            ny, nx = cy + dy[i], cx + dx[i]
            if 0 <= ny < N and 0 <= nx < N:
                # 현재까지 아는 최소 비용보다 비용이 작으면 갱신
                next_cost = curr_cost + board[ny][nx] 
                if dist[ny][nx] > next_cost:
                    dist[ny][nx] = next_cost
                    heapq.heappush(q, [dist[ny][nx], ny, nx])

    print(f"Problem {case}: {dist[-1][-1]}")
    case += 1