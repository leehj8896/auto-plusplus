import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 스도쿠 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int[][] puzzle = new int[9][9];

        for (int i = 0; i < 9; i++) {
            String temp = in.readLine();

            for (int j = 0; j < 9; j++) {
                puzzle[i][j] = temp.charAt(j) - '0';
            }
        }

        solve(0, 0, puzzle);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(puzzle[i][j]);
            }
            System.out.println();
        }

        in.close();
    }

    // 칸 하나씩 들어갈 수 있는 숫자 모두 시도, 불가능하다면 백트래킹
    public static boolean solve(int r, int c, int[][] puzzle) {
        if (r == 9) {
            return true;
        } else if (c == 9){
            return solve(r + 1, 0, puzzle);
        } else if (puzzle[r][c] != 0) {
            return solve(r, c + 1, puzzle);
        }

        boolean[] isUsed = check(r, c, puzzle);

        for (int i = 1; i <= 9; i++) {
            if (!isUsed[i]) {
                puzzle[r][c] = i;
                if (solve(r, c + 1, puzzle)) {
                    return true;
                }
            }
        }

        puzzle[r][c] = 0;

        return false;
    }

    // 기로, 세로, 3x3 구역 사용 숫자 확인
    public static boolean[] check(int r, int c, int[][] puzzle) {
        boolean[] isUsed = new boolean[10];

        // 가로 확인
        for (int i = 0; i < 9; i++) {
            if (c == i) {
                continue;
            }

            if (puzzle[r][i] != 0) {
                isUsed[puzzle[r][i]] = true;
            }
        }

        // 세로 확인
        for (int i = 0; i < 9; i++) {
            if (r == i) {
                continue;
            }

            if (puzzle[i][c] != 0) {
                isUsed[puzzle[i][c]] = true;
            }
        }

        // 3x3 확인
        for (int i = r / 3 * 3; i < r / 3 * 3 + 3; i++) {
            for (int j = c / 3 * 3; j < c / 3 * 3 + 3; j++) {
                if (r == i && c == j) {
                    continue;
                }

                if (puzzle[i][j] != 0) {
                    isUsed[puzzle[i][j]] = true;
                }
            }
        }

        return isUsed;
    }

}
