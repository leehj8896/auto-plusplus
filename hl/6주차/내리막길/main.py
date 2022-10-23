h, w = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(h)]
dy, dx = [-1,0,1,0], [0,1,0,-1]

count = [[-1]*w for _ in range(h)]

def dfs(cy, cx):

    if cy == h-1 and cx == w-1:
        return 1
    
    # 이미 갔던 위치이면
    if count[cy][cx] != -1:
        return count[cy][cx]
    
    count[cy][cx] = 0

    for i in range(4):
        ny = cy + dy[i]
        nx = cx + dx[i]
        if 0 <= ny < h and 0 <= nx < w:
            if board[cy][cx] > board[ny][nx]:
                count[cy][cx] += dfs(ny, nx)
    
    return count[cy][cx]


dfs(0,0)
print(count[0][0])

'''
4 5
50 45 37 32 30
35 50 40 20 25
30 30 25 17 28
27 24 22 15 10

'''