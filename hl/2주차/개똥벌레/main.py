from bisect import bisect_left
import sys

# 입력 받기
input = sys.stdin.readline
W, H = map(int, input().split())
tops, bottoms = [], []
for i in range(W):
    length = int(input())
    if i % 2 == 0:
        bottoms.append(length)
    else:
        tops.append(length)
tops.sort()
bottoms.sort()

최소만나는개수 = float('inf')
구간의수 = 0

# 모든 y, y+1 탐색
# 해당 y에서 처음 만나는 x값 탐색
for y in range(H):
    x1 = bisect_left(tops, y+1)             # top이 y+1보다 내려오는 인덱스
    x2 = bisect_left(bottoms, H-y)          # bottom이 y보다 올라가는 인덱스
    만나는개수 = (W//2 - x1) + (W//2 - x2)
    if 최소만나는개수 > 만나는개수:
        최소만나는개수 = 만나는개수
        구간의수 = 1
    elif 최소만나는개수 == 만나는개수:
        구간의수 += 1

print(최소만나는개수, 구간의수)