import java.util.*;

public class 주차요금계산 {
    public int[] solution(int[] fees, String[] records) {
        int[] answer;

        Map<String, Integer> in = new HashMap<>(); // 입차
        Map<String, Integer> out = new HashMap<>(); // 출차
        PriorityQueue<Car> queue = new PriorityQueue<>((o1, o2) -> o1.num.compareTo(o2.num));

        for (String record : records) {
            StringTokenizer st = new StringTokenizer(record);

            String time = st.nextToken();
            String num = st.nextToken();
            String flag = st.nextToken();

            st = new StringTokenizer(time, ":");

            // 분 단위로 변경
            int minute = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());

            if (flag.equals("IN")) {
                in.put(num, minute);
            } else {
                int useTime = minute - in.get(num); // 사용 시간 계산

                in.remove(num);
                out.put(num, out.getOrDefault(num, 0) + useTime); // out 해쉬맵에 차 번호별로 시간 누적
            }
        }

        // 23:59 출차 처리
        for (String num : in.keySet()) {
            int useTime = 23 * 60 + 59 - in.get(num);

            out.put(num, out.getOrDefault(num, 0) + useTime);
        }

        // 정렬을 위해 큐에 넣기
        for (String num : out.keySet()) {
            queue.add(new Car(num, out.get(num)));
        }

        answer = new int[queue.size()];
        int index = 0;

        while (!queue.isEmpty()) {
            answer[index++] = getFee(fees, queue.poll().minute);
        }

        return answer;
    }

    // 주차 요금을 계산하는 메서드
    public static int getFee(int[] fees, int useTime) {
        int fee = fees[1];

        useTime -= fees[0];

        // 기본 시간 초과
        if (useTime > 0) {
            fee += (useTime / fees[2]) * fees[3] + ((useTime % fees[2] == 0) ? 0 : fees[3]);
        }

        return fee;
    }

    static class Car {
        String num;
        int minute;

        Car(String num, int minute) {
            this.num = num;
            this.minute = minute;
        }
    }
}
