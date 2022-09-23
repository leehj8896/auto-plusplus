# 입력
N = int(input())
mats = []
for _ in range(N):
    r, c = map(int, input().split())
    mats.append([r,c])

# 초기화
min_values = [[float('inf')] * N for _ in range(N)]
for i in range(N):
    min_values[i][i] = 0
    if i+1 < N:
        min_values[i][i+1] = mats[i][0] * mats[i][1] * mats[i+1][1]

for start in range(2, N):
    i = 0
    j = start
    # 대각선으로 내려감
    while i < N and j < N:
        # 최솟값 구하기
        for k in range(i, j):
            min_values[i][j] = min(
                min_values[i][j],
                min_values[i][k] + min_values[k+1][j] + mats[i][0]*mats[k][1]*mats[j][1]
            )
        i += 1
        j += 1

print(min_values[0][-1])