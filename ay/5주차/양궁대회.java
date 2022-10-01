import java.util.Arrays;

public class 양궁대회 {
    static int[] answer = new int[11];
    static int[] selected = new int[11];
    static int distance = 0;

    public int[] solution(int n, int[] info) {

        shoot(0, 0, 0, 0, n, info);

        return distance == 0 ? new int[]{-1} : answer;
    }

    // 10점부터 차례대로 가능한 점수를 모두 시도해보는 메서드
    public static void shoot(int num, int cnt, int apeach, int lion, int n, int[] info) {
        if (cnt == 11) {
            if (apeach < lion && distance <= lion - apeach) {
                // 점수 차 비교
                if (distance > lion - apeach) {
                    return;
                } else if (distance == lion - apeach) {
                    // 가장 낮은 점수 비교
                    for (int i = 10; i >= 0; i--) {
                        if (selected[i] != 0 || answer[i] != 0) {
                            if (selected[i] <= answer[i]) {
                                return;
                            }

                            break;
                        }
                    }
                }

                answer = selected.clone();
                distance = lion - apeach;
            }

            return;
        }

        // 0부터 가능한 점수까지 시도
        for (int i = 0; i + num <= n; i++) {
            selected[cnt] = i;

            // 어피치 점수보다 높은 경우
            if (info[cnt] < i) {
                shoot(i + num, cnt + 1, apeach, lion + (10 - cnt), n, info);
            } else {
                // 둘 다 0인 경우
                if (info[cnt] == 0) {
                    shoot(i + num, cnt + 1, apeach, lion, n, info);
                } else {
                    shoot(i + num, cnt + 1, apeach + (10 - cnt), lion, n, info);
                }
            }
        }
    }
}
