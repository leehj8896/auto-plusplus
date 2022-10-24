#h, w = map(int, input().split())
#board = [list(map(int, input().split())) for _ in range(h)]
#'''
h, w = [4,5]
board = [
  [50,45,37,32,30],
  [35,50,40,20,25],
  [30,30,25,17,24],
  [27,24,22,15,10],
]
#'''

dy, dx = [-1,0,1,0], [0,1,0,-1]

visited = [[0]*w for _ in range(h)]
count = [[0]*w for _ in range(h)]

def dfs(cy, cx):
    
    if cy == h-1 and cx == w-1:
        count[cy][cx] = 1 
        return 1
    
    for i in range(4):
        ny = cy + dy[i]
        nx = cx + dx[i]
        if 0 <= ny < h and 0 <= nx < w:
            if board[cy][cx] > board[ny][nx]:
                if not visited[ny][nx]:
                    print(ny, nx, board[ny][nx])
                    visited[ny][nx] = 1
                    count[cy][cx] += dfs(ny, nx)
                else:
                    count[cy][cx] += count[ny][nx]
    
    return count[cy][cx]

visited[0][0] = 1
dfs(0,0)
print(count[0][0])

for row in count:
    print(row)
