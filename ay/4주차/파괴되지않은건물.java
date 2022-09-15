public class 파괴되지않은건물 {

    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length;
        int M = board[0].length;

        final int ATTACK = 1;
        int[][] dp = new int[N + 1][M + 1];

        for (int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = (type == ATTACK ? -1 : 1) * skill[i][5];

            dp[r1][c1] += degree;
            dp[r1][c2 + 1] += degree * -1;
            dp[r2 + 1][c1] += degree * -1;
            dp[r2 + 1][c2 + 1] += degree; // 겹치는 부분
        }

        // 세로 누적
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j] += dp[i - 1][j];
            }
        }

        // 가로 누적
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] += dp[i][j - 1];
            }
        }

        // 건물 내구도 체크
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] += dp[i][j];

                if (board[i][j] > 0) {
                    answer++;
                }
            }
        }

        return answer;
    }
}