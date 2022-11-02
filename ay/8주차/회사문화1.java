import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 회사문화1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] employees = new ArrayList[n + 1];
        st = new StringTokenizer(in.readLine());

        for (int i = 1; i <= n; i++) {
            employees[i] = new ArrayList<>();

            int boss = Integer.parseInt(st.nextToken());

            if (boss != -1) {
                employees[boss].add(i);
            }
        }

        int[] scores = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());

            int index = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());

            scores[index] += amount;
        }

        compliment(1, employees, scores);


        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(scores[i] + " ");
        }

        System.out.println(sb);

        in.close();
    }

    public static void compliment(int index, ArrayList<Integer>[] employees, int[] scores) {
        for (int junior : employees[index]) {
            scores[junior] += scores[index];
            compliment(junior, employees, scores);
        }
    }
}