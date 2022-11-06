import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;

public class PacificAtlanticWaterFlow {
    static int m, n;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static final int PACIFIC = 0;
    static final int ATLANTIC = 1;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        m = heights.length;
        n = heights[0].length;

        boolean[][][] flags = new boolean[m][n][2]; // 도달 가능 여부 저장

        // 각 바다에 대해 bfs
        bfs(PACIFIC, heights, flags);
        bfs(ATLANTIC, heights, flags);

        List<List<Integer>> answer = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (flags[i][j][PACIFIC] && flags[i][j][ATLANTIC]) {
                    List<Integer> points = new ArrayList<Integer>();
                    points.add(i);
                    points.add(j);
                    answer.add(points);
                }
            }
        }

        return answer;
    }

    // 인접한 구역 탐색
    public static void bfs(int sea, int[][] heights, boolean[][][] flags) {
        Queue<Point> queue = new LinkedList<>();

        // 바다에서부터 시작
        addSide(sea, flags, queue);

        while (!queue.isEmpty()) {
            Point temp = queue.poll();
            int r = temp.r;
            int c = temp.c;

            for (int i = 0; i < 4; i++) {
                int tempR = r + dr[i];
                int tempC = c + dc[i];

                if (tempR >= 0 && tempC >= 0 && tempR < m && tempC < n) {
                    if (heights[tempR][tempC] >= heights[r][c] && !flags[tempR][tempC][sea]) {
                        flags[tempR][tempC][sea] = true;
                        queue.add(new Point(tempR, tempC));
                    }
                }
            }
        }
    }

    // 해당 바다에 인접한 구역 추가
    public static void addSide(int sea, boolean[][][] flags, Queue<Point> queue) {
        if (sea == PACIFIC) { // PACIFIC
            queue.add(new Point(0, 0));
            flags[0][0][sea] = true;

            for (int i = 1; i < m; i++) {
                queue.add(new Point(i, 0));
                flags[i][0][sea] = true;
            }

            for (int i = 1; i < n; i++) {
                queue.add(new Point(0, i));
                flags[0][i][sea] = true;
            }
        } else { // ATLANTIC
            queue.add(new Point(m -1, n - 1));
            flags[m-1][n-1][sea] = true;

            for (int i = 0; i < m - 1; i++) {
                queue.add(new Point(i, n-1));
                flags[i][n - 1][sea] = true;
            }

            for (int i = 0; i < n - 1; i++) {
                queue.add(new Point(m - 1, i));
                flags[m-1][i][sea] = true;
            }
        }
    }

    static class Point {
        int r;
        int c;

        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}