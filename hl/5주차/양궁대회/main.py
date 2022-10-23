# 백트래킹, 완전탐색
def solution(n, info):
    global score, answer
    lion = [0] * 11
    score = -1
    answer = [9] * 11

    def dfs(i, remains):
        global score, answer
        # 끝에 도달
        if i == len(lion):
            # 남는 화살 0점에 다 쏨
            shot = sum(lion)
            if shot < n:
                lion[-1] += n-shot

            # 점수 계산
            score1 = 0  # 어피치 점수
            score2 = 0  # 라이언 점수
            for j in range(len(lion)):
                if info[j] == 0 and lion[j] == 0:
                    continue
                if info[j] < lion[j]:
                    score2 += 10-j
                else:
                    score1 += 10-j
            
            # 높은 점수로 갱신
            diff = score2 - score1
            if diff > score:
                score = diff
                answer = lion[:]
            elif diff == score:
                for j in range(10,-1,-1):
                    if answer[j] < lion[j]:
                        answer = lion[:]
                        break
                    elif answer[j] > lion[j]:
                        break
            return

        # j발 쏨
        for j in range(remains+1): # 0,1,2,3,4, ..., 10 (n=10)
            lion[i] = j
            dfs(i+1, remains-j)
            lion[i] = 0
    
    dfs(0, n)

    if score <= 0:
        return [-1]
    return answer

print(solution(
    9, [0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1]
))