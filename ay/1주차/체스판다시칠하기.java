import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class 체스판다시칠하기 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String temp = in.readLine();
			
			for (int j = 0; j < M; j++) {
				map[i][j] = temp.charAt(j) == 'B';
			}
		}
		
		int answer = Integer.MAX_VALUE;
		
		for (int i = 0; i <= N - 8; i++) {
			for (int j = 0; j <= M - 8; j++) {
				answer = Math.min(check(i, j, map), answer);
			}
		}
		
		System.out.println(answer);
		
		
		in.close();
	}

	public static int check(int r, int c, boolean[][] map) {
		int cnt = 0;
		
		boolean color = map[r][c];
		
		for (int i = r; i < r + 8; i++) {
			for (int j = c; j < c + 8; j++) {
				if (color != map[i][j]) {
					cnt++;
				}
				
				// 다음 색 바꾸기
				color = !color;
			}
			
			// 다음 줄 시작 색 바꾸기
			color = !color;
		}
		
		
		return Math.min(cnt, 64 - cnt);
	}
}
