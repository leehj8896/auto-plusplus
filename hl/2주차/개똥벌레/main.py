
# 입력받기
import sys
readline = sys.stdin.readline
N, H = map(int, readline().split())
tops = []
bottoms = []
for i in range(N):
    length = int(readline())
    # 바닥이면
    if i % 2 == 0:
        bottoms.append(length)
    # 천장이면
    else:
        tops.append(length)
tops.sort()
bottoms.sort()
print('tops     ', tops)
print('bottoms  ', bottoms)

# 이분탐색 (만나는 개수는 증가함) (처음 만나는 위치 찾기)

left = 0
right = N // 2

print("left", left)
print("right", right)

def meet(i):
    height1 = H - tops[i]
    height2 = bottoms[i]
    if height1 < height2:
        return True
    return False


while True:
    mid = (left + right) // 2
    print("mid", mid)

    if mid-1 < 0:
        break
    if mid >= N // 2:
        break

    if not meet(mid-1) and meet(mid):
        break
    if meet(mid-1) and meet(mid):           # 왼쪽으로
        right = mid
    if not meet(mid-1) and not meet(mid):   # 오른쪽으로
        left = mid