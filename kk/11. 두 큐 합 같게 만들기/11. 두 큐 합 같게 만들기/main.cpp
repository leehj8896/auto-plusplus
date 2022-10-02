//
//  main.cpp
//  11. 두 큐 합 같게 만들기
//
//  Created by dohan on 2022/10/02.
//

#include <string>
#include <vector>
#include <queue>

using namespace std;

queue<int> q1;
queue<int> q2;
long long cnt;

int solution(vector<int> queue1, vector<int> queue2) {
    
    long long sum1 =0;
    long long sum2 =0;
    for(int i=0;i<queue1.size(); i++){
        sum1 += queue1[i];
        sum2 += queue2[i];
        q1.push(queue1[i]);
        q2.push(queue2[i]);
    }
    
    if((sum1+sum2) % 2) return -1;
    
    while(sum1 != sum2){
        if(cnt > queue1.size() + queue2.size() +1) return -1;
        if(sum1 < sum2){
            q1.push(q2.front());
            sum1 += q2.front();
            sum2 -= q2.front();
            q2.pop();
        }else {
            q2.push(q1.front());
            sum2 += q1.front();
            sum1 -= q1.front();
            q1.pop();
        }
        
        cnt ++;
    }
    
    
    return cnt;
}
