import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 비용 기준으로 확보 가능한 최대 메모리를 계산하는 방법
 */

public class 앱2 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int sum = 0;
        int[] memory = new int[N + 1];
        int[] cost = new int[N + 1];

        st = new StringTokenizer(in.readLine());
        StringTokenizer st2 = new StringTokenizer(in.readLine());

        for (int i = 1; i <= N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st2.nextToken());

            sum += cost[i];
        }

        int[][] dp = new int[N + 1][sum + 1]; // dp[i][j] = i번째 앱까지 고려했을 때 j 비용을 사용하면 확보할 수 있는 최대 메모리

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= sum; j++) {
                if (cost[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost[i]] + memory[i]);
                }
            }
        }

        int answer = 0;

        // M 메모리를 확보할 수 있는 최소 비용 찾기
        for (int i = 1; i <= sum; i++) {
            if (dp[N][i] >= M) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);

        in.close();
    }

}