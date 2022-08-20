import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 괄호 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());

        StringBuffer answer = new StringBuffer("");

        for (int i = 0; i < N; i++) {
            boolean result = true;
            String temp = in.readLine();
            Stack<Character> stack = new Stack<>();

            for (int j = 0; j < temp.length(); j++) {
                if (temp.charAt(j) == '(') {
                    stack.add('(');
                } else {
                    if (stack.isEmpty()) {
                        result = false;
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }

            if (stack.isEmpty() && result) { // 남은 괄호 없는지 확인
                answer.append("YES\n");
            } else {
                answer.append("NO\n");
            }
        }

        System.out.println(answer);

        in.close();
    }

}
