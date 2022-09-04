import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 녹색옷입은애가젤다지 {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder("");

        int T = 0;
        final int INF = 1000000;

        while (true) {
            T++;

            int N = Integer.parseInt(in.readLine());

            if (N == 0) {
                break;
            }

            int[][] map = new int[N][N];
            int[][] cost = new int[N][N]; // cost[i][j] = (i, j) 까지 이동하는데 드는 최소 비용

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());

                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }

                Arrays.fill(cost[i], INF);
            }

            cost[0][0] = 0;

            sb.append("Problem " + T + ": " + bfs(cost, map, N) + "\n");
        }

        System.out.println(sb);

        in.close();
    }


    public static int bfs(int[][] cost, int[][] map, int N) {
        PriorityQueue<Point> queue = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        queue.add(new Point(0, 0, 0));

        while (!queue.isEmpty()) {
            Point temp = queue.poll();

            if (temp.r == N - 1 && temp.c == N - 1) {
                return temp.cost + map[N - 1][N - 1];
            }

            for (int i = 0; i < 4; i++) {
                int tempR = temp.r + dr[i];
                int tempC = temp.c + dc[i];

                if (tempR >= 0 && tempR  < N && tempC >= 0 && tempC < N
                        && cost[tempR][tempC] > map[temp.r][temp.c] + temp.cost) {
                    // 최소 비용 갱신
                    cost[tempR][tempC] = map[temp.r][temp.c] + temp.cost;
                    queue.add(new Point(tempR, tempC, temp.cost + map[temp.r][temp.c]));
                }
            }
        }

        return -1;
    }

    static class Point{
        int r;
        int c;
        int cost;

        Point(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }
}