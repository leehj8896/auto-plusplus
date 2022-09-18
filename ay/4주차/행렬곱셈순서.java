import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 행렬곱셈순서 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        int[][] matrix = new int[N][2];
        int[][] dp = new int[N][N]; // dp[i][j] 행렬 i부터 j까지 곱셈하는데 필요한 곱셈 연산 횟수의 최솟값

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());

            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int gap = 1; gap < N; gap++) {
            for (int start = 0; start + gap < N; start++) {
                dp[start][start + gap] = Integer.MAX_VALUE;

                for (int mid = start; mid < start + gap; mid++) {
                    dp[start][start + gap] = Math.min(dp[start][start + gap],
                            dp[start][mid] + dp[mid + 1][start + gap] + matrix[start][0] * matrix[mid][1] * matrix[start + gap][1]);
                }
            }
        }

        System.out.println(dp[0][N - 1]);

        in.close();

    }
}
