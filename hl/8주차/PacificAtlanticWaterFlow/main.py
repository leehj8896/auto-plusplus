from collections import deque

class Solution:
    def pacificAtlantic(self, heightss: list[list[int]]) -> list[list[int]]:
        global h, w, heights, checked, answer
        heights = heightss
        h = len(heights)
        w = len(heights[0])

        checked = [[False for _ in range(w)] for _ in range(h)]

        answer = []
        for i in range(h):
            for j in range(w):
                if self.flow(i,j):
                    answer.append([i,j])
        return answer

    def flow(self, sy, sx):
        global dy, dx, visited
        dy,dx = [0,0,1,-1],[1,-1,0,0]
        visited = [[0]*w for _ in range(h)]
        visited[sy][sx] = 1

        q = deque()
        q.append([sy,sx])

        r1, r2 = False, False

        if checked[sy][sx]:
            return True

        while q:
            cy,cx = q.popleft()
            for i in range(4):
                ny, nx = cy+dy[i], cx+dx[i]
                if 0<=ny<h and 0<=nx<w:
                    if heights[cy][cx] >= heights[ny][nx]:
                        if not visited[ny][nx]:
                            visited[ny][nx] = 1
                            q.append([ny,nx])

                # 태평양
                if (ny < 0) or (nx < 0 and ny < h):
                    r1 = True
                # 대서양
                if (ny >= h) or (nx >= w and ny >= 0):
                    r2 = True

        if r1 and r2:
            return True
        return False


    def dfs(self, cy, cx):

        for i in range(4):
            ny, nx = cy+dy[i], cx+dx[i]
            if 0<=ny<h and 0<=nx<w:
                if heights[cy][cx] >= heights[ny][nx]:
                    if not visited[ny][nx]:
                        visited[ny][nx] = 1
                        self.dfs(ny, nx)

            # 태평양
            if (ny < 0) or (nx < 0 and ny < h):
                checked[cy][cx][0] = True
            # 대서양
            if (ny >= h) or (nx >= w and ny >= 0):
                checked[cy][cx][1] = True


heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
s = Solution()
s.pacificAtlantic(heights)