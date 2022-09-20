import java.util.LinkedList;
import java.util.Queue;

public class 두큐합같게만들기 {
    public int solution(int[] queue1, int[] queue2) {
        long answer = 0;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sum1 = makeQueue(q1, queue1);
        long sum2 = makeQueue(q2, queue2);

        while (answer != queue1.length * 3) {
            if (sum1 == sum2) {
                break;
            } else if (sum1 > sum2) {
                sum1 -= q1.peek();
                sum2 += q1.peek();
                q2.add(q1.poll());
            } else {
                sum2 -= q2.peek();
                sum1 += q2.peek();
                q1.add(q2.poll());
            }

            answer++;
        }

        return answer == queue1.length * 3 ? -1 : (int)answer;
    }

    public static long makeQueue(Queue<Integer> q, int[] queue) {
        long sum = 0;

        for (int i = 0; i < queue.length; i++) {
            q.add(queue[i]);
            sum += queue[i];
        }

        return sum;
    }
}
