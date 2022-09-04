import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 경쟁적전염 {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];

        // 증식하지 않은 바이러스를 저장하는 우선순위 큐
        PriorityQueue<Virus> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] != 0) {
                    queue.add(new Virus(i, j, map[i][j], 0));
                }
            }
        }

        st = new StringTokenizer(in.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        while (!queue.isEmpty()) {
            Virus temp = queue.poll();

            // 주어진 시간을 넘어가면 중단
            if (temp.time >= S) {
                break;
            }

            // 상하좌우 탐색
            for (int dir  = 0; dir < 4; dir++) {
                int tempR = temp.r + dr[dir];
                int tempC = temp.c + dc[dir];

                // 아직 감염되지 않은 공간이라면 감염시킨 후 큐에 추가
                if (tempR >= 0 && tempR < N && tempC >= 0 && tempC < N && map[tempR][tempC] == 0) {
                    map[tempR][tempC] = temp.type;
                    queue.add(new Virus(tempR, tempC, map[tempR][tempC], temp.time + 1));
                }
            }
        }

        System.out.println(map[X - 1][Y - 1]);

        in.close();
    }

    // 바이러스 정보를 저장하는 클래스
    static class Virus implements Comparable<Virus> {
        int r;
        int c;
        int type; // 바이러스 종류
        int time; // 바이러스 발생 시간

        public Virus(int r, int c, int type, int time) {
            this.r = r;
            this.c = c;
            this.type = type;
            this.time = time;
        }

        /**
         * 시간, 유형 순서대로 비교
         */
        @Override
        public int compareTo(Virus o) {
            if (o.time == time) {
                return type - o.type;
            }

            return time - o.time;
        }
    }

}
