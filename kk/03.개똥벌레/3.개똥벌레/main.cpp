//
//  main.cpp
//  3.개똥벌레
//
//  Created by dohan on 2022/08/30.
//

#include <iostream>

using namespace std;

int N,H,cnt;
int top[500001];
int bottom[500001];
int sum[500001];
int main(void) {
    cin >> N >> H;
    for(int i=0; i<N/2; i++){
        int b,t;
        cin >> b >> t;
        top[H-t+1] ++ ;
        bottom[b] ++;
    }
    
    for(int i=H; i>0; i--){
        bottom[i-1] += bottom[i];
        top[H-i+2] += top[H-i+1];
        
    }
    
    //합해서 젤 작은거 찾음
    int min = N;
    for(int i=1; i<=H; i++){
        if(bottom[i] + top[i] < min){
            min = bottom[i] + top[i];
            cnt = 1;
        }else if(bottom[i] + top[i] == min){
            cnt ++;
        }
    }
    
    
    cout << min << " " << cnt;
    return 0;
}
