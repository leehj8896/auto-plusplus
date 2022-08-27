import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 개똥벌레 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] up = new int[H + 1]; // 석순 (위를 향함)
        int[] down = new int[H + 1]; // 종유석 (아래를 향함)

        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(in.readLine());

            if (i % 2 == 0) {
                up[height]++;
            } else {
                down[height]++;
            }
        }

        int answer = Integer.MAX_VALUE;
        int cnt = 0;

        // 누적합 구하기
        for (int i = H - 1; i > 0; i--) {
            up[i] += up[i + 1];
            down[i] += down[i + 1];
        }

        // 각 높이마다의 장애물 개수 구하기
        for (int i = 1; i <= H; i++) {
            int temp = up[i] + down[H - i + 1];

            if (temp < answer) {
                cnt = 1;
                answer= temp;
            } else if (temp == answer) {
                cnt++;
            }
        }

        System.out.println(answer + " " + cnt);

        in.close();
    }
}
