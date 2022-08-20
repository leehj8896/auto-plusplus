import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 외판원순회 {
	
	static int N;
	static int[][] W, dp;
	static final int INF = 100000000;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		W = new int[N][N]; // W[현재도시][도착도시] = 현재 도시에서 도착 도시로 가는데 드는 비용
		dp = new int[N][1 << N]; // dp[현재도시][방문여부bit] = 나머지 도시를 방문하는데 드는 비용
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 어느 도시에서 시작하든 동일한 결과가 나오기 때문에 시작 도시 고정
		System.out.println(go(0, 1));
		
		in.close();
	}
	
	public static int go(int current, int bit) {
		// 이미 탐색한 경우의 수
		if (dp[current][bit] != 0) {
			return dp[current][bit];
		}
		
		// 모든 도시를 방문했으면 첫 번째 도시로 이동
		if (bit == (1 << N) - 1) {
			return W[current][0] == 0 ? INF : W[current][0];
		}
		
		// 최대 값으로 초기화
		dp[current][bit] = INF;
		
		for (int i = 0; i < N; i++) {
			// 현재 도시에서 갈 수 있는 도시이며, 아직 방문하지 않았다면
			if (W[current][i] != 0 && (bit & (1 << i)) == 0) {
				dp[current][bit] = Math.min(dp[current][bit], W[current][i] + go(i, bit | (1 << i)));
			}
		}
		
		return dp[current][bit];
	}
}
