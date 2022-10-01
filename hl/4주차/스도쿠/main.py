# 입력받기
board = []
for i in range(9):
    row = list(map(int, input()))
    board.append(row)

# 0인 좌표 정리
blank = []
for i in range(9):
    for j in range(9):
        if board[i][j] == 0:
            blank.append([i,j])

def possible(i, j, k):
    sy = (i // 3) * 3
    sx = (j // 3) * 3
    # 사각형 체크
    for cy in range(sy, sy+3):
        for cx in range(sx, sx+3):
            if board[cy][cx] == k:
                return False
    # 세로줄 체크
    for cy in range(9):
        if board[cy][j] == k:
            return False
    # 가로줄 체크
    for cx in range(9):
        if board[i][cx] == k:
            return False
    
    return True


def dfs(curr):
    # 끝까지 도달할 경우 정답 출력
    if curr == len(blank):
        for row in board:
            print(''.join(map(str, row)))
        exit()

    cy, cx = blank[curr]

    # 다 넣어봄
    for i in range(1, 10):
        # i 가능하면 넣어보기
        if possible(cy,cx,i):
            board[cy][cx] = i
            dfs(curr+1)
            board[cy][cx] = 0

dfs(0)