import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 할당 가능한 메모리 용량을 기준으로 최소 비용을 저장하는 방법
 */

public class 앱 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 메모리 M 바이트를 확보하기 위한 앱 비활성화의 최소의 비용, 메모리 절약을 위해 이전 상태와 현재 상태만 저장
        int[][] dp = new int[2][M + 1];

        int[] memory = new int[N + 1];
        int[] cost = new int[N + 1];

        final int INF = 1000000001;

        st = new StringTokenizer(in.readLine());
        StringTokenizer st2 = new StringTokenizer(in.readLine());

        for (int i = 1; i <= N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st2.nextToken());
        }

        Arrays.fill(dp[0], INF);
        dp[0][0] = 0;

        // 이전 상태는 0, 현재 상태는 1로 저장
        int past = 0;
        int current = 1;

        for (int i = 1; i <= N; i++) {
            // j 바이트 메모리를 확보할 수 있는 최소 비용 계산
            for (int j = 0; j <= M; j++) {
                // j 바이트보다 확보할 수 있는 메모리가 큰 경우
                if (memory[i] >= j) {
                    dp[current][j] = Math.min(dp[past][j], cost[i]); // 이전 상태의 값과 비교하여 최솟값 저장

                // j 바이트보다 확보할 수 있는 메모리가 작은 경우
                } else {
                    // 이전 상태의 값이 INF인 경우, 비교 없이 저장 (오버플로우 방지)
                    if (dp[past][j - memory[i]] == INF) {
                        dp[current][j] = dp[past][j];
                    } else {
                        // 현재 앱을 비활성했을 때와 비활성화하지 않았을 때의 비용 비교하여 최솟값 저장
                        dp[current][j] = Math.min(dp[past][j], dp[past][j - memory[i]] + cost[i]);
                    }
                }
            }

            // 이전 상태와 현재 상태 swap
            past = current;
            current = current == 0 ? 1 : 0;
        }

        System.out.println(dp[N % 2][M]);

        in.close();
    }

}