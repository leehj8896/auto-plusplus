import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 징검다리건너기 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        final int USED = 0;     // 큰 점프 사용 O
        final int UNUSED = 1;   // 큰 점프 사용 X
        final int INF = 100001;

        int N = Integer.parseInt(in.readLine());

        int[][] energies = new int[N][2];
        // dp[i][USED] = i까지 갈 수 있는 최소 에너지 (큰 점프 사용), dp[i][UNUSED] = i까지 갈 수 있는 최소 에너지 (큰 점프 사용 X)
        int[][] dp = new int[N + 3][2];

        for (int i = 1; i <= N - 1; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());

            energies[i][0] = Integer.parseInt(st.nextToken());
            energies[i][1] = Integer.parseInt(st.nextToken());
        }

        // 최댓값으로 초기화
        for (int i = 2; i < N + 3; i++) {
            Arrays.fill(dp[i], INF);
        }

        int K = Integer.parseInt(in.readLine());

        // 첫 번째 돌부터 다음 돌로 이동
        for (int i = 1; i < N; i++) {
            // 큰 점프 사용하지 않은 경우의 수 2가지
            dp[i + 1][UNUSED] = Math.min(dp[i + 1][UNUSED], dp[i][UNUSED] + energies[i][0]);
            dp[i + 2][UNUSED] = Math.min(dp[i + 2][UNUSED], dp[i][UNUSED] + energies[i][1]);

            // 큰 점프 사용한 경우의 수 3가지
            dp[i + 1][USED] = Math.min(dp[i + 1][USED], dp[i][USED] + energies[i][0]);
            dp[i + 2][USED] = Math.min(dp[i + 2][USED], dp[i][USED] + energies[i][1]);
            dp[i + 3][USED] = Math.min(dp[i][UNUSED] + K, dp[i + 3][USED]); // 지금 큰 점프 사용
        }

        System.out.println(Math.min(dp[N][USED], dp[N][UNUSED]));

        in.close();
    }

}
