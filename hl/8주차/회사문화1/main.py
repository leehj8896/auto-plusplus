import sys
input = sys.stdin.readline
print = sys.stdout.write
sys.setrecursionlimit(10000)

n, m = map(int, input().split())
parents = [0] + list(map(int, input().split()))
# 그래프
graph = [[] for _ in range(n+1)]
for c in range(1, n+1):
    p = parents[c]
    if p != -1:
        graph[p].append(c)
# 점수
scores = [0] * (n+1)
for _ in range(m):
    i, w = map(int, input().split())
    scores[i] += w

def dfs(i):
    for j in graph[i]:
        scores[j] += scores[i]  # 부모점수 -> 자식점수
        dfs(j)

dfs(1)
for i in range(1, n+1):
    print(f'{scores[i]} ')