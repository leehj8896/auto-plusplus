import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 벽부수고이동하기 {

    static final int ROAD = 0;
    static final int WALL = 1;
    static final int USED = 0;
    static final int UNUSED = 1;
    static int[] dr = {-1 , 1, 0, 0};
    static int[] dc = {0, 0, -1 , 1};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String temp = in.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = temp.charAt(j) - '0';
            }
        }

        System.out.println(bfs(N, M, map));

        in.close();
    }

    public static int bfs(int N, int M, int[][] map) {
        boolean[][][] isVisited = new boolean[N][M][2];
        isVisited[0][0][UNUSED] = true;

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0,  UNUSED));

        int dist = 0;

        while (!queue.isEmpty()) {
            int cnt = queue.size();
            dist++;

            for (int c = 0; c < cnt; c++) {
                Point temp = queue.poll();

                if (temp.r == N - 1 && temp.c == M - 1) {
                    return dist;
                }

                for (int i = 0; i < 4; i++) {
                    int tempR = temp.r + dr[i];
                    int tempC = temp.c + dc[i];

                    if (tempR >= 0 && tempR < N && tempC >= 0 && tempC < M) {
                        // 다음 경로가 길일 경우
                        if (map[tempR][tempC] == ROAD && !isVisited[tempR][tempC][temp.flag]) {
                            isVisited[tempR][tempC][temp.flag] = true;
                            queue.add(new Point(tempR, tempC,  temp.flag));
                        // 다음 경로가 벽일 경우, 이전에 벽을 부수지 않았어야 함
                        } else if (temp.flag == UNUSED && !isVisited[tempR][tempC][USED]) {
                            isVisited[tempR][tempC][USED] = true;
                            queue.add(new Point(tempR, tempC, USED));
                        }
                    }
                }
            }
        }

        return -1;
    }

    public static class Point {
        int r;
        int c;
        int flag; // 벽 통과 여부

        Point(int r, int c, int flag) {
            this.r = r;
            this.c = c;
            this.flag = flag;
        }
    }

}
