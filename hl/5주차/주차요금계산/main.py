def solution(fees, records):

    in_time = dict()
    total_time = dict()

    for row in records:
        time_string, car_num, in_out = row.split()
        
        # 입차일 경우
        if in_out == "IN":
            in_time[car_num] = string_to_num(time_string)
        # 출차일 경우
        else:
            t1 = in_time[car_num]
            t2 = string_to_num(time_string)

            if car_num not in total_time:
                total_time[car_num] = 0

            total_time[car_num] += t2 - t1

            in_time.pop(car_num)

    # 안 나온 차량
    for car_num in in_time:
        t1 = in_time[car_num]
        t2 = string_to_num("23:59")
        if car_num not in total_time:
            total_time[car_num] = 0
        total_time[car_num] += t2 - t1


    기본시간,기본요금,단위시간,단위요금 = fees

    total_fee = dict()
    for car_num in total_time:
        t = total_time[car_num]

        if t <= 기본시간:
            total_fee[car_num] = 기본요금
        else:
            plus_time = t - 기본시간
            몫 = plus_time // 단위시간
            나머지 = plus_time % 단위시간

            total_fee[car_num] = 기본요금
            total_fee[car_num] += 몫 * 단위요금
            if 나머지 > 0:
                total_fee[car_num] += 단위요금

    answer = list(total_fee.items())
    answer.sort(key=lambda x:x[0])
    return [row[1] for row in answer]

def string_to_num(string):
    hour, minute = map(int, string.split(":"))
    return hour * 60 + minute

print(
    solution(
        [180, 5000, 10, 600],
        ["05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"],
    )
)