from collections import deque

def solution(queue1, queue2):

    q1 = deque(queue1)
    q2 = deque(queue2)

    s1 = sum(q1)
    s2 = sum(q2)

    count = 0

    while s1 != s2:

        count += 1

        if s1 > s2:
            c1 = q1.popleft()
            q2.append(c1)

            s1 -= c1
            s2 += c1

        else:
            c2 = q2.popleft()
            q1.append(c2)

            s1 += c2
            s2 -= c2

        # 왜 되는지 모르겠음
        if count >= 300000:
            return -1

    return count


queue1 = [3, 2, 7, 2]
queue2 = [4, 6, 5, 1]

queue1 = [1, 2, 1, 2]	
queue2 = [1, 10, 1, 2]

queue1 = [1, 1]	
queue2 = [1, 5]

print(
    solution(
        queue1, queue2
    )
)