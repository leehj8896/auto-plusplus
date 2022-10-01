def solution(board, skill):

    for row in skill:
        is_attack, y1, x1, y2, x2, degree = row

        # 공격일 경우
        if is_attack == 1:
            for i in range(y1, y2+1):
                for j in range(x1, x2+1):
                    board[i][j] -= degree
        # 회복일 경우
        elif is_attack == 2:
            for i in range(y1, y2+1):
                for j in range(x1, x2+1):
                    board[i][j] -= degree

    answer = 0
    return answer